package com.example.newsapp.data.impl

import android.util.Log
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.ui.UIState
import com.example.newsapp.utils.others.checkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsNetworkImpl @Inject constructor(val newsApi: NewsApi): NewsRepository {
    @Throws
    override suspend fun fetchNews(start: Int): Flow<List<Article>> {

           return flow{
               val news=newsApi.getTopNews()
               if(!news.checkError()) {
                   emit(news.body())
               }
           }
               .map {
                   Log.e("response","$it")
                   it?.articles?:emptyList()
               }
               .flowOn(Dispatchers.IO)


    }

    override suspend fun searchNews(search: String): Flow<UIState<List<Article>>> {
        return flow {
            emit(UIState.Loading)

            val news = newsApi.searchNews(search) // assuming this API exists

            if(!news.checkError()) {
                val data = news.body()?.articles ?: emptyList()
                emit(UIState.Success(data))
            }

        }.catch { e ->
            emit(UIState.Failure(e.message))
        }.flowOn(Dispatchers.IO)
    }


}

