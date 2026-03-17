package com.example.newsapp.ui.commonUi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.ui.UIState
import okhttp3.Response

@Composable
fun NewsUiList(listResponse: UIState<List<Article>>){
    when(listResponse){
        is UIState.Loading->{
            ShowLoading(stringResource(R.string.loading_news))
        }

        is UIState.Failure->{
            ShowError(listResponse.msg)
        }

        is UIState.Success -> {
            val items=listResponse.data
            NewsList(items)

        }
        else->{

        }
    }


}