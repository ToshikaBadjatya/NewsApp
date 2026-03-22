package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.navigation.MainDestinations
import com.example.newsapp.viewmodels.DatabaseNewsViewmodel

@Composable
fun SavedItemsScreen(navController: NavController) {
    val newsViewModel: DatabaseNewsViewmodel = hiltViewModel()
    LaunchedEffect(Unit) {
        newsViewModel.fetchTopHeadlines()
    }
    val pagingList = newsViewModel._newsPagingItem.collectAsLazyPagingItems()
    NewsPaginationList(pagingList, onItemClick = { article ->
        navController.navigate(MainDestinations.NewsDetail.createRoute(article.url))
    })
}