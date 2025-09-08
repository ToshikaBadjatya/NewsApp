package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.models.News
import com.example.newsapp.utils.NetworkConstants
import com.example.newsapp.utils.NetworkConstants.DEFAULT_PAGE_NUM
import com.example.newsapp.utils.NetworkConstants.DEFAULT_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getTopNews(
        @Query("country") country: String ,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): News

    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getNewsByLanguage(
        @Query("language") language: String ,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): News

    @GET(NetworkConstants.TOP_HEADLINES)
    suspend fun getNewsBySource(
        @Query("sources") sources: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): News


    @GET(NetworkConstants.EVERYTHING)
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
    ): News


}