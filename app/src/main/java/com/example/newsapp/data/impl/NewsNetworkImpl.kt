package com.example.newsapp.data.impl

import android.content.Context
import android.util.Log
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.News
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.ui.UIState
import com.example.newsapp.utils.others.CustomErrorClass
import com.example.newsapp.utils.others.Utils
import com.example.newsapp.utils.others.parseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsNetworkImpl @Inject constructor(val dispatchersProvider: DispatchersProvider,val newsApi: NewsApi,val utils: Utils): NewsRepository {
    @Throws
    override suspend fun fetchNews(start: Int): Flow<List<Article>> {

           return flow{
               if(!utils.hasInternet()){
                   throw CustomErrorClass.NoInternet
               }
               val news=newsApi.getTopNews()
               if(news.isSuccessful) {
                   emit(news.body())
               }
               else{
                   throw (parseError(news.errorBody()))
               }
           }
               .map {
                   Log.e("response","$it")
                   it?.articles?:emptyList()
               }
               .flowOn(dispatchersProvider.io)


    }

    override suspend fun searchNews(search: String): Flow<UIState<List<Article>>> {
        return flow {
            emit(UIState.Loading)

            if(!utils.hasInternet()){
                throw CustomErrorClass.NoInternet
            }
            val news = newsApi.searchNews(search) // assuming this API exists

            if(news.isSuccessful) {
                val data = news.body()?.articles ?: emptyList()
                emit(UIState.Success(data))
            }
            else{
                throw (parseError(news.errorBody()))
            }

        }.catch { e ->
            emit(UIState.Failure(e.message))
        }.flowOn(dispatchersProvider.io)
    }


}

