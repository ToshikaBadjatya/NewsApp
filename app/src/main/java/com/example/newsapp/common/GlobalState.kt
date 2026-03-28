package com.example.newsapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.remote.models.Article

object GlobalState {

    private val _savedArticles = MutableLiveData<List<Article>>(emptyList())
    val savedArticles: LiveData<List<Article>> = _savedArticles
    fun addToSaved(articles: List<Article>) {
        val currentList = _savedArticles.value.orEmpty().toMutableList()

        // Avoid duplicates (optional but recommended)
            currentList.addAll(articles)
            _savedArticles.value = currentList

    }
    fun addToSaved(article: Article) {
        val currentList = _savedArticles.value.orEmpty().toMutableList()

        // Avoid duplicates (optional but recommended)
        if (currentList.none{it-> it.title==article.title}) {
            currentList.add(article)
            _savedArticles.value = currentList
        }
    }
    fun canSave(article: Article): Boolean{
        val currentList = _savedArticles.value.orEmpty().toMutableList()
        if(currentList.isEmpty()){
            return true
        }

       return currentList.none{it-> it.title==article.title}
    }

    fun removeFromSaved(article: Article) {
        val currentList = _savedArticles.value.orEmpty().toMutableList()
        currentList.removeAll {it-> it.title==article.title}
        _savedArticles.value = currentList
    }
}