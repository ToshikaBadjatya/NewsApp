package com.example.newsapp.data.impl

import android.util.Log
import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.local.database.ArticleEntity
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.Filters
import com.example.newsapp.data.remote.models.Source
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.ui.UIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsDatabaseImpl @Inject constructor(val newsDao: NewsDao) : NewsRepository {

    override suspend fun fetchNews(start: Int): Flow<List<Article>> = flow {
        val entities=newsDao.getAllEntities().map { it.toArticle() }
        emit(entities)
    }

    override suspend fun searchNews(search: String): Flow<UIState<List<Article>>> = flow {
        emit(UIState.Success(emptyList()))
    }

    override suspend fun fetchByFilter(filter: Filters): Flow<UIState<List<Article>>> {
        return flow {  }
    }

    override suspend fun saveArticles(article: Article) {
        newsDao.insertEntity(article.toEntity())

    }

    suspend fun deleteArticle(article: Article) {
        newsDao.deleteEntity(article.toEntity())
    }
}

private fun ArticleEntity.toArticle() = Article(
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

private fun Article.toEntity() = ArticleEntity(
    source =source,
    author = author,
    title = title ?: "",
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)
