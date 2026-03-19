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

    @Query("SELECT * FROM articles")
    suspend fun getAllEntities(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAllEntities()


}