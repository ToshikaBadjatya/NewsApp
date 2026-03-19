package com.example.newsapp.ui.commonUi

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.models.Article
import androidx.compose.ui.res.stringResource

@Composable
fun NewsPaginationList(pagingList: LazyPagingItems<Article>){
    val response=pagingList.loadState.refresh
    when(response){
        is LoadState.Loading->{
           ShowLoading(stringResource(R.string.loading_news))
        }

        is LoadState.Error->{
           ShowError(response.error.message)
        }

        else -> {
            val items=pagingList.itemSnapshotList.items
             NewsList(items)

        }
    }



}