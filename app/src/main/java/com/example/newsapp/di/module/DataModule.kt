package com.example.newsapp.di.module

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsapp.data.impl.NewsDatabaseImpl
import com.example.newsapp.data.impl.NewsNetworkImpl
import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.paging.NewsPagingSource
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.di.Database
import com.example.newsapp.di.Network
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.utils.constants.NetworkConstants
import com.example.newsapp.utils.others.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    @Network
    fun getNetworkPager(pagingConfig: PagingConfig,@Network pagingSource: NewsPagingSource): Pager<Int, Article>{
        return Pager(pagingConfig){
            pagingSource
        }
    }
    @Provides
    fun getPagingConfig() : PagingConfig{
        return PagingConfig(NetworkConstants.DEFAULT_PAGE_SIZE)
    }
    @Provides
    @Network
    fun getNetworkNewsPagingSource(@Network newsRepository: NewsRepository,dispatchersProvider: DispatchersProvider) : NewsPagingSource{
        return NewsPagingSource(dispatchersProvider){page ->
            newsRepository.fetchNews(page)
        }
    }
    @Provides
    @Database
    fun getDatabaseNewsPagingSource(@Database newsRepository: NewsRepository,dispatchersProvider: DispatchersProvider) : NewsPagingSource{
        return NewsPagingSource(dispatchersProvider){page->
            newsRepository.fetchNews(page)
        }
    }


    @Provides
    @Network
    fun getNewsRepositoryNetwork(newsApi: NewsApi,utils: Utils,dispatchersProvider: DispatchersProvider): NewsRepository{
        return NewsNetworkImpl(dispatchersProvider,newsApi,utils)
    }
    @Provides
    @Database
    fun getNewsRepositoryDb(newsDao: NewsDao): NewsRepository{
        return NewsDatabaseImpl(newsDao)
    }
}