package com.example.newsapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.navigation.NavController
import com.example.newsapp.navigation.MainDestinations
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.viewmodels.NetworkNewsViewmodel

@Composable
fun TopHeadlineScreen(navController: NavController) {
    val newsViewModel: NetworkNewsViewmodel = hiltViewModel()
    LaunchedEffect(Unit) {
        newsViewModel.fetchTopHeadlines()
    }
    val pagingList = newsViewModel._newsPagingItem.collectAsLazyPagingItems()
    NewsPaginationList(pagingList, onItemClick = { article ->
        navController.navigate(MainDestinations.NewsDetail.createRoute(article.url))
    })
}

