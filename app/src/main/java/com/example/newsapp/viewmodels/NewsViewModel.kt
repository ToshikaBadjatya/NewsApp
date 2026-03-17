package com.example.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(@Network val newsRepository: NewsRepository,
                                        @Network val networkPager: Pager<Int, Article>): ViewModel() {
    private val news= MutableStateFlow<UIState<List<Article>>>(UIState.Idle)
    val _news: StateFlow<UIState<List<Article>>> =news

    private val newsPagingItem= MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val _newsPagingItem: StateFlow<PagingData<Article>> =newsPagingItem

     fun fetchTopHeadlines(){
         viewModelScope.launch {
            networkPager.flow.cachedIn(viewModelScope)
                .catch {  }
                .map {
                    it.filter {
                        !it.title.isNullOrEmpty()
                    }
                }
                .collect { newsPagingItem.value=it }
         }
    }
}