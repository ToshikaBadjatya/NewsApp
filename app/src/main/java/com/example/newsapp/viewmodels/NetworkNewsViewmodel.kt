package com.example.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class NetworkNewsViewmodel @Inject constructor(@Network override val newsRepository: NewsRepository,
                                               @Network override val networkPager: Pager<Int, Article>): NewsViewModel(newsRepository,networkPager)  {
}