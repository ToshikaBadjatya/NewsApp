package com.example.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.ui.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


open class NewsViewModel @Inject constructor(
    open val newsRepository: NewsRepository,
    open val networkPager: Pager<Int, Article>,
    open val logger: Logger,
    open val dispatcherProvider: DispatchersProvider

) : ViewModel() {
    private val news = MutableStateFlow<UIState<List<Article>>>(UIState.Idle)
    val _news: StateFlow<UIState<List<Article>>> = news

    private val newsPagingItem = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val _newsPagingItem: StateFlow<PagingData<Article>> = newsPagingItem

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            networkPager.flow.cachedIn(viewModelScope)
                .map {
                    it.filter {
                        !it.title.isNullOrEmpty()
                    }
                }
                .flowOn(dispatcherProvider.io)
                .collect { newsPagingItem.value = it }
        }
    }

    fun search(search: String) {
        viewModelScope.launch {
            newsRepository.searchNews(search)
                .flowOn(dispatcherProvider.io)
                .collect { news.value = it }
        }

    }
}