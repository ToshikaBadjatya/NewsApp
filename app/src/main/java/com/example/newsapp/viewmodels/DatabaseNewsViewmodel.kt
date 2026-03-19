package com.example.newsapp.viewmodels

import androidx.paging.Pager

import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.di.Database
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DatabaseNewsViewmodel @Inject constructor(@Database override val newsRepository: NewsRepository,
                                                @Database override val networkPager: Pager<Int, Article>): NewsViewModel(newsRepository,networkPager)  {
}