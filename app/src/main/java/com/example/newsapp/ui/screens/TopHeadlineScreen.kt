package com.example.newsapp.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.navigation.MainDestinations
import com.example.newsapp.ui.commonUi.NewsPaginationList
import com.example.newsapp.viewmodels.DatabaseNewsViewmodel
import com.example.newsapp.viewmodels.NetworkNewsViewmodel

@Composable
fun TopHeadlineScreen(goToDetail: (Article) -> Unit) {
    val newsViewModel: NetworkNewsViewmodel = hiltViewModel()
    val databaseViewmodel: DatabaseNewsViewmodel = hiltViewModel()
    LaunchedEffect(Unit) {
        newsViewModel.fetchTopHeadlines()
    }
    val pagingList = newsViewModel._newsPagingItem.collectAsLazyPagingItems()
    NewsPaginationList(pagingList, onItemClick = { article ->
        goToDetail.invoke(article)
    },
        onSave = { article ->
            Log.e("article","save called")
            databaseViewmodel.saveArticles(article)
        }
        )
}

