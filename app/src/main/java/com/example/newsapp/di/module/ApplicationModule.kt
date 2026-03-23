package com.example.newsapp.di.module


import android.content.Context
import com.example.newsapp.interfaces.AppLogger
import com.example.newsapp.interfaces.DefaultDispatchersProvider
import com.example.newsapp.interfaces.DispatchersProvider
import com.example.newsapp.interfaces.Logger
import com.example.newsapp.utils.others.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @Singleton
    fun getLogger(): Logger{
        return AppLogger()
    }

    @Provides
    fun getDispatchersProvider(): DispatchersProvider{
        return DefaultDispatchersProvider()
    }
    @Provides
    @Singleton
    fun provideUtils(@ApplicationContext context: Context): Utils {
        return Utils(context)
    }


}