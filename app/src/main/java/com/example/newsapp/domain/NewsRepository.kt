package com.example.newsapp.domain

import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.ui.UIState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchNews(start: Int): Flow<List<Article>>
    suspend fun searchNews(search:String): Flow<UIState<List<Article>>>

}