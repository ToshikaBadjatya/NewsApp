package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.models.News
import com.example.newsapp.utils.constants.NetworkConstants
import com.example.newsapp.utils.constants.NetworkConstants.DEFAULT_CATEGORY
import com.example.newsapp.utils.constants.NetworkConstants.DEFAULT_PAGE_NUM
import com.example.newsapp.utils.constants.NetworkConstants.DEFAULT_PAGE_SIZE
import com.example.newsapp.utils.constants.NetworkConstants.DEFAULT_COUNTRY
import com.example.newsapp.utils.constants.NetworkConstants.DEFAULT_LANGUAGE
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getTopNews(
        @Query("country") country: String=DEFAULT_COUNTRY ,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): retrofit2.Response<News>
    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getNewsByCountry(
        @Query("country") country: String=DEFAULT_COUNTRY ,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): retrofit2.Response<News>

    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getNewsByLanguage(
        @Query("language") language: String=DEFAULT_LANGUAGE ,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): retrofit2.Response<News>

    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getNewsByCategory(
        @Query("category") category: String=DEFAULT_CATEGORY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): retrofit2.Response<News>


    @GET(NetworkConstants.EVERYTHING)
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): retrofit2.Response<News>


}