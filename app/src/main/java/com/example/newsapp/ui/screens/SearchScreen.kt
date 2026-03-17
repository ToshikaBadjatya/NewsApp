package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.R
import com.example.newsapp.ui.commonUi.NewsList
import com.example.newsapp.ui.commonUi.NewsUiList
import com.example.newsapp.viewmodels.NewsViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(FlowPreview::class)
@Composable
fun SearchScreen() {
    val search= remember{
        mutableStateOf("")
    }
    val newsViewModel: NewsViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        snapshotFlow { search.value }
            .debounce(1000)
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .collect { query ->
                newsViewModel.search(search.value)
            }
    }

    Search(search.value,
        {
            search.value=it
        }
    ){
        val newsState=newsViewModel._news.collectAsStateWithLifecycle()
        NewsUiList(newsState.value)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    contentToShow: @Composable ColumnScope.() -> Unit,) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        contentToShow()
    }
}