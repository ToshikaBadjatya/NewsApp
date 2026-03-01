package com.example.newsapp.ui.commonUi

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun NewsPaginationList(){
    LazyColumn {
        items((1..10).toList()){
            NewsItem()
        }
    }

}