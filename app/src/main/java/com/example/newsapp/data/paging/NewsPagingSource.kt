package com.example.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.interfaces.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

    class NewsPagingSource@Inject constructor(val dispatchersProvider: DispatchersProvider,val callback:suspend (Int)-> Flow<List<Article>>): PagingSource<Int, Article>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
            val page = params.key ?: 1
            lateinit var loadResult: LoadResult<Int, Article>
            runCatching {
                val data = callback.invoke(page)
                    .flowOn(dispatchersProvider.io)
                    .first()

                loadResult=LoadResult.Page(
                    data = data,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = page + 1
                )
            }.onFailure {
                loadResult= LoadResult.Error(it)
            }

            return loadResult


        }

        override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
            return state.anchorPosition
        }
    }