package com.example.newsapp.domain

import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchNews(start: Int): Flow<List<Article>>
    suspend fun searchNews(start: Int): Flow<List<Article>>

}