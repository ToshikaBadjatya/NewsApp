package com.example.newsapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.local.database.NewsDatabase
import com.example.newsapp.di.DbName
import com.example.newsapp.utils.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        application: Application,
        @DbName dbName: String
    ): NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            dbName
        )
            .build()
    }
    @Singleton
    @Provides
    fun getNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }
    @DbName
    @Provides
    fun provideDbName(): String = Constants.DB_NAME

}