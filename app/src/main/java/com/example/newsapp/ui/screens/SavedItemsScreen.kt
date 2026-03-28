package com.example.newsapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.navigation.MainDestinations
import com.example.newsapp.viewmodels.DatabaseNewsViewmodel

@Composable
fun SavedItemsScreen(goToDetail: (Article) -> Unit) {
    val newsViewModel: DatabaseNewsViewmodel = hiltViewModel()
    LaunchedEffect(Unit) {
        newsViewModel.fetchTopHeadlines()
    }
    val pagingList = newsViewModel._newsPagingItem.collectAsLazyPagingItems()
    NewsPaginationList(pagingList, onItemClick = { article ->
        goToDetail(article)
    })
}