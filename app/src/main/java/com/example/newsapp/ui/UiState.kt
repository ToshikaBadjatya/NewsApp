package com.example.newsapp.ui

sealed interface UIState<out T> {
    data class Success<T>(val data: T) : UIState<T>
    data class Failure<T>(val msg: String? = null, val data: T? = null) : UIState<T>
    object Loading : UIState<Nothing>
    object Idle : UIState<Nothing>
}
