package com.example.newsapp.data.impl

import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDatabaseImpl @Inject constructor(val newsDao: NewsDao): NewsRepository {
    override suspend fun fetchNews(start: Int): Flow<List<Article>> {
        TODO("Not yet implemented")

    }

    override suspend fun searchNews(start: Int): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}