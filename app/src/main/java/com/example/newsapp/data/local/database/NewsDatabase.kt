package com.example.newsapp.data.local.database

import androidx.room.Database
import androidx.room.Entity
import com.example.newsapp.data.local.dao.NewsDao

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = true)
abstract class NewsDatabase {
    abstract fun newsDao(): NewsDao

}