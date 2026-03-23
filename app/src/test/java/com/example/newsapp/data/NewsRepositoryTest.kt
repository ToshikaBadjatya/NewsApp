package com.example.newsapp.data

import com.example.newsapp.data.impl.NewsDatabaseImpl
import com.example.newsapp.data.impl.NewsNetworkImpl
import com.example.newsapp.data.impl.toEntity
import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.local.database.ArticleEntity
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.data.remote.models.Source
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.interfaces.TestLogger
import com.example.newsapp.ui.UIState
import com.example.newsapp.utils.others.CustomErrorClass
import com.example.newsapp.utils.others.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    @Mock
    lateinit var newsApi: NewsApi

    @Mock
    lateinit var newsDao: NewsDao

    @Mock
    lateinit var utils: Utils

    private lateinit var dispatchersProvider: DispatchersProvider
    private lateinit var logger: Logger
    private lateinit var networkRepository: NewsNetworkImpl
    private lateinit var databaseRepository: NewsDatabaseImpl

    @Before
    fun init() {
        Dispatchers.setMain(TestDispatchersProvider().main)
        dispatchersProvider = TestDispatchersProvider()
        logger = TestLogger()
        databaseRepository = NewsDatabaseImpl(dispatchersProvider, newsDao, logger)
        networkRepository = NewsNetworkImpl(dispatchersProvider, newsApi, utils, logger)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun testNetworkFetchNews_whenApiReturnsSuccess() = runTest {
        val response = News(totalResults = TestData.articleList.size, articles = TestData.articleList)
        whenever(utils.hasInternet()).thenReturn(true)
        whenever(newsApi.getTopNews()).thenReturn(Response.success(response))

        val result = networkRepository.fetchNews(0).first()
        assertEquals(TestData.articleList, result)
    }

    @Test
    fun testNetworkFetchNews_whenNoInternet() = runTest {
        whenever(utils.hasInternet()).thenReturn(false)
        try {
             networkRepository.fetchNews(0)
        }catch (e: Exception){
            assertEquals(CustomErrorClass.NoInternet, e)

        }
    }

    @Test
    fun testDatabaseFetchNews_whenRecordsExists() = runTest {
        whenever(newsDao.getAllEntities()).thenReturn(TestData.articleList.map { it.toEntity() })

        val result = databaseRepository.fetchNews(0).first()
        assertEquals(TestData.articleList, result)
    }
    @Test
    fun testDatabaseFetchNews_whenRecordsDoesNotExists() = runTest {
        whenever(newsDao.getAllEntities()).thenReturn(emptyList<ArticleEntity>())

        val result = databaseRepository.fetchNews(0).first()
        assertEquals(emptyList<Article>(), result)
    }

}

