package com.example.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.local.dao.NewsDao
import com.example.newsapp.data.local.database.SourceConverters

@Database(entities = [ArticleEntity::class], version = 2, exportSchema = false)
@TypeConverters(SourceConverters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}