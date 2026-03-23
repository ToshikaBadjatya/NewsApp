package com.example.newsapp.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.data.remote.models.Source
import com.google.gson.Gson

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val articleId: Int = 0,
    val source: Source?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
class SourceConverters {
    @TypeConverter
    fun fromSource(source: Source): String = Gson().toJson(source)

    @TypeConverter
    fun toSource(json: String): Source = Gson().fromJson(json, Source::class.java)
}