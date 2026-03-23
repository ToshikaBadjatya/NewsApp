package com.example.newsapp.data.impl

import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.local.database.ArticleEntity
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.Filters
import com.example.newsapp.data.remote.models.Source
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsDatabaseImpl @Inject constructor(
    val dispatchersProvider: DispatchersProvider,
    val newsDao: NewsDao,
    val logger: Logger
) : NewsRepository {

    override suspend fun fetchNews(start: Int): Flow<List<Article>> = flow {
        val entities = newsDao.getAllEntities().map { it.toArticle() }
        logger.log("NewsDatabaseImpl", "fetchNews: ${entities.size} items")
        emit(entities)
    }.flowOn(dispatchersProvider.io)

    override suspend fun searchNews(search: String): Flow<UIState<List<Article>>> = flow {
        val results = newsDao.searchEntities(search).map { it.toArticle() }
        logger.log("NewsDatabaseImpl", "searchNews: query=$search, results=${results.size}")
        emit(UIState.Success(results))
    }.flowOn(dispatchersProvider.io)

    override suspend fun fetchByFilter(filter: Filters): Flow<UIState<List<Article>>> = flow {
        val results = newsDao.getAllEntities().map { it.toArticle() }
        logger.log("NewsDatabaseImpl", "fetchByFilter: filter=$filter, results=${results.size}")
        emit(UIState.Success(results))
    }.flowOn(dispatchersProvider.io)

    override suspend fun saveArticles(article: Article) {
        logger.log("NewsDatabaseImpl", "saveArticles: ${article.url}")
        newsDao.insertEntity(article.toEntity())
    }

    suspend fun deleteArticle(article: Article) {
        logger.log("NewsDatabaseImpl", "deleteArticle: ${article.url}")
        newsDao.deleteByUrl(article.url)
    }
}

 fun ArticleEntity.toArticle() = Article(
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

 fun Article.toEntity() = ArticleEntity(
    source =source,
    author = author,
    title = title ?: "",
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)
