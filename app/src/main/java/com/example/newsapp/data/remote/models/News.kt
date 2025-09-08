package com.example.newsapp.data.remote.models

data class News(
    val articles: List<Article>,
    val totalResults: Int,
)
