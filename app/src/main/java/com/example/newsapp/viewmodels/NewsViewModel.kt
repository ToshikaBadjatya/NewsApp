package com.example.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepository: NewsRepository): ViewModel() {
}