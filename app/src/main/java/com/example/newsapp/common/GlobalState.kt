package com.example.newsapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.remote.models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

object GlobalState {

    private val _savedArticles = MutableStateFlow<List<Article>>(emptyList())
    val savedArticles: StateFlow<List<Article>> = _savedArticles

    fun addToSaved(articles: List<Article>) {
        val currentList = _savedArticles.value.toMutableList()

        // Add all (no duplicate check here, same as your original)
        currentList.addAll(articles)

        _savedArticles.value = currentList
    }

    fun addToSaved(article: Article) {
        _savedArticles.update {currentList->
            if (currentList.none { it.title == article.title }) {
                currentList+article
            }
            else currentList

        }

    }

    fun canSave(article: Article): Boolean {
        val currentList = _savedArticles.value
        return currentList.none { it.title == article.title }
    }

    fun removeFromSaved(article: Article) {
        _savedArticles.update {currentList->
            currentList.filterNot { it.title == article.title }
        }
    }
}