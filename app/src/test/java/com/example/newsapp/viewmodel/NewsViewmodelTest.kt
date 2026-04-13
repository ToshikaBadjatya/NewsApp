package com.example.newsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.testing.asSnapshot
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.example.newsapp.data.TestData
import com.example.newsapp.data.TestDispatchersProvider
import com.example.newsapp.data.TestErrorPagingSource
import com.example.newsapp.data.TestPagingSource
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.interfaces.TestLogger
import com.example.newsapp.ui.UIState
import com.example.newsapp.utils.others.CustomErrorClass
import com.example.newsapp.viewmodels.NewsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewsViewmodelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsRepository: NewsRepository

    lateinit var networkPager: Pager<Int, Article>
    lateinit var networkErrorPager: Pager<Int, Article>

    private lateinit var logger: Logger
    private lateinit var dispatchersProvider: DispatchersProvider

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        logger = TestLogger()
        dispatchersProvider = TestDispatchersProvider()
        networkPager = Pager(PagingConfig(pageSize = 10)) { TestPagingSource() }
        networkErrorPager = Pager(PagingConfig(pageSize = 10)) { TestErrorPagingSource() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // --- Search tests ---

    @Test
    fun testSearchNews_emptyQuery_repositoryReturnsSuccess() = runTest {
        whenever(newsRepository.searchNews(""))
            .thenReturn(flowOf(UIState.Success(emptyList())))

        val viewModel = NewsViewModel(newsRepository, networkPager, logger, dispatchersProvider)
        viewModel.search("")

        viewModel._news.test {
            assertEquals(UIState.Success(emptyList<Article>()), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(newsRepository, times(1)).searchNews("")
    }

    @Test
    fun testSearchNews_withQuery_repositoryReturnsSuccess() = runTest {
        val query = "abc"
        whenever(newsRepository.searchNews(query))
            .thenReturn(flowOf(UIState.Success(TestData.articleList)))

        val viewModel = NewsViewModel(newsRepository, networkPager, logger, dispatchersProvider)
        viewModel.search(query)

        viewModel._news.test {
            assertEquals(UIState.Success(TestData.articleList), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(newsRepository, times(1)).searchNews(query)
    }

    @Test
    fun testSearchNews_withQuery_repositoryReturnsError() = runTest {
        val query = "abc"
        whenever(newsRepository.searchNews(query))
            .thenReturn(flowOf(UIState.Failure(CustomErrorClass.ServerError)))

        val viewModel = NewsViewModel(newsRepository, networkPager, logger, dispatchersProvider)
        viewModel.search(query)

        viewModel._news.test {
            assertEquals(UIState.Failure<Article>(CustomErrorClass.ServerError), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(newsRepository, times(1)).searchNews(query)
    }

    // --- Paging tests ---

    @Test
    fun testFetchTopHeadlines_pagingDataContainsExpectedItems() = runTest {
        val viewModel = NewsViewModel(newsRepository, networkPager, logger, dispatchersProvider)
        viewModel.fetchTopHeadlines()

        val snapshot: List<Article> = viewModel._newsPagingItem.asSnapshot()

        val expected = TestData.articleList.filter { !it.title.isNullOrEmpty() }
        assertEquals(expected, snapshot)
    }

    @Test
    fun testFetchTopHeadlines_pagingDataHasError() = runTest {

        val viewModel = NewsViewModel(newsRepository, networkErrorPager, logger, dispatchersProvider)
        viewModel.fetchTopHeadlines()

        runCatching {
            viewModel._newsPagingItem.asSnapshot()
            assertTrue("Expected an exception but none was thrown", false)
        }.onFailure { it->
            assertEquals(it, CustomErrorClass.ServerError)
        }
    }

}
