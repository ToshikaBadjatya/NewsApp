package com.example.newsapp.di.module

import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.network.ApiKeyInterceptor
import com.example.newsapp.di.ApiKey
import com.example.newsapp.di.BaseUrl
import com.example.newsapp.utils.constants.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.OptionalConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun getNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient,
                    @BaseUrl url: String,
                     converterFactory: GsonConverterFactory): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun getGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun getOkHttClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .readTimeout(4, TimeUnit.SECONDS)
            .writeTimeout(4, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    fun getApiKeyInterceptor(@ApiKey apiKey: String): Interceptor{
      return ApiKeyInterceptor(apiKey)
    }
    @BaseUrl
    @Provides
    fun getBaseUrl()= NetworkConstants.BASE_URL

    @ApiKey
    @Provides
    fun getApiKey()= NetworkConstants.API_KEY
}