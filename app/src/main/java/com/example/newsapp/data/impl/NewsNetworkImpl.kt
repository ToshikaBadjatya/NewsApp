package com.example.newsapp.data.impl

import android.util.Log
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.Filters
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.ui.UIState
import com.example.newsapp.utils.others.CustomErrorClass
import com.example.newsapp.utils.others.Utils
import com.example.newsapp.utils.others.toCustomError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsNetworkImpl @Inject constructor(
    val dispatchersProvider: DispatchersProvider,
    val newsApi: NewsApi,
    val utils: Utils,
    val logger: Logger
) : NewsRepository {

    override suspend fun fetchNews(start: Int): Flow<List<Article>> = flow {
        if (!utils.hasInternet()) throw CustomErrorClass.NoInternet
        val response = newsApi.getTopNews()
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            throw response.toCustomError()
        }
    }
        .map {
            logger.log("NewsNetworkImpl", "fetchNews response: $it")
            it?.articles ?: emptyList()
        }
        .flowOn(dispatchersProvider.io)

    override suspend fun searchNews(search: String): Flow<UIState<List<Article>>> = flow {
        emit(UIState.Loading)
        if (!utils.hasInternet()) throw CustomErrorClass.NoInternet
        val response = newsApi.searchNews(search)
        if (response.isSuccessful) {
            logger.log("NewsNetworkImpl", "searchNews success: query=$search")
            emit(UIState.Success(response.body()?.articles ?: emptyList()))
        } else {
            throw response.toCustomError()
        }
    }.catch { e ->
        logger.log("NewsNetworkImpl", "searchNews error: ${e.message}")
        emit(UIState.Failure((e as? CustomErrorClass)?.msg ?: e.message))
    }.flowOn(dispatchersProvider.io)

    override suspend fun fetchByFilter(filter: Filters): Flow<UIState<List<Article>>> = flow {
        emit(UIState.Loading)
        if (!utils.hasInternet()) throw CustomErrorClass.NoInternet
        val response = when (filter) {
            is Filters.Language -> newsApi.getNewsByLanguage(filter.value)
            is Filters.Country  -> newsApi.getTopNews(country = filter.value)
            is Filters.Category -> newsApi.getNewsByCategory(filter.value)
        }
        if (response.isSuccessful) {
            logger.log("NewsNetworkImpl", "fetchByFilter success: filter=$filter")
            emit(UIState.Success(response.body()?.articles ?: emptyList()))
        } else {
            throw response.toCustomError()
        }
    }.catch { e ->
        logger.log("NewsNetworkImpl", "fetchByFilter error: ${e.message}")
        emit(UIState.Failure((e as? CustomErrorClass)?.msg ?: e.message))
    }.flowOn(dispatchersProvider.io)

    override suspend fun saveArticles(article: Article) {
        logger.log("NewsNetworkImpl", "saveArticles: ${article.url}")
    }
}
