package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.viewmodels.DatabaseNewsViewmodel
import com.example.newsapp.viewmodels.NewsViewModel

@Composable
fun SavedItemsScreen(){
    val newsViewModel: DatabaseNewsViewmodel = hiltViewModel()
    LaunchedEffect(Unit) {
        newsViewModel.fetchTopHeadlines()
    }
    val pagingList=newsViewModel._newsPagingItem.collectAsLazyPagingItems()
    NewsPaginationList(pagingList)
}