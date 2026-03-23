package com.example.newsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.utils.others.CustomErrorClass

class TestPagingSource() : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return LoadResult.Page(data = TestData.articleList, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}
class TestErrorPagingSource() : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return LoadResult.Error(CustomErrorClass.ServerError)
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}
