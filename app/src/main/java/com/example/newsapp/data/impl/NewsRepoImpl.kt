package com.example.newsapp.data.impl

import com.example.newsapp.data.remote.models.News
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl: NewsRepository {
    override suspend fun fetchNews(start: Int): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNews(start: Int): Flow<List<News>> {
        TODO("Not yet implemented")
    }
}