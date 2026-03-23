package com.example.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.newsapp.data.local.database.ArticleEntity

@Dao
interface NewsDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertEntity(entity: ArticleEntity)

    @Delete
    suspend fun deleteEntity(entity: ArticleEntity)

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Query("SELECT * FROM articles")
    suspend fun getAllEntities(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    suspend fun searchEntities(query: String): List<ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAllEntities()


}