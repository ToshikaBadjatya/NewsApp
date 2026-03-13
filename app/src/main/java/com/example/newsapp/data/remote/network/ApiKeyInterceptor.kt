package com.example.newsapp.data.remote.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("X-Api-Key", apiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}