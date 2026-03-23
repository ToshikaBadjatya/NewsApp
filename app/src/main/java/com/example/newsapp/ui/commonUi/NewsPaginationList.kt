package com.example.newsapp.ui.commonUi

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.remote.models.Article
import androidx.compose.ui.res.stringResource
import com.example.newsapp.ui.UIState

@Composable
fun NewsPaginationList(pagingList: LazyPagingItems<Article>, onItemClick: (Article) -> Unit = {},onSave: ((Article) -> Unit)? = null) {
    val response = pagingList.loadState.refresh
    when (response) {
        is LoadState.Loading -> ShowLoading(stringResource(R.string.loading_news))
        is LoadState.Error -> ShowError(response.error.message)
        else -> NewsList(pagingList.itemSnapshotList.items, onItemClick = onItemClick,onSave)
    }
}

@Composable
fun NewsUiList(state: UIState<List<Article>>, onItemClick: (Article) -> Unit = {},onSave: ((Article) -> Unit)? = null) {
    when (state) {
        is UIState.Loading -> ShowLoading(stringResource(R.string.loading_news))
        is UIState.Failure -> ShowError(state.msg)
        is UIState.Success -> NewsList(state.data, onItemClick = onItemClick,onSave)
        is UIState.Idle -> {}
    }
}
