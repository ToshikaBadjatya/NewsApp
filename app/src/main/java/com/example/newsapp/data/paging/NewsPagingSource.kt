package com.example.newsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

    class NewsPagingSource@Inject constructor(val callback:suspend (Int)-> Flow<List<Article>>): PagingSource<Int, Article>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
          var page=params.key?:1
            return try {
                var data: List<Article> = emptyList()

                callback.invoke(page)
                    .flowOn(Dispatchers.IO)
                    .catch { throw it }
                    .collect {
                        data = it
                    }

                LoadResult.Page(
                    data,
                    if (page > 1) page - 1 else null,
                    page + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
            return state.anchorPosition
        }
    }