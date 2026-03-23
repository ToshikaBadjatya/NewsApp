package com.example.newsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.Filters
import com.example.newsapp.ui.commonUi.NewsUiList
import com.example.newsapp.viewmodels.DatabaseNewsViewmodel
import com.example.newsapp.viewmodels.NetworkNewsViewmodel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

// Default instances used to represent each filter type in the UI
private val filterOptions = listOf(
    Filters.Category,
    Filters.Country,
    Filters.Language
)

@Composable
fun FilterScreen(goToDetail: (Article) -> Unit) {
    val newsViewModel: NetworkNewsViewmodel = hiltViewModel()
    val newsState = newsViewModel._news.collectAsStateWithLifecycle()
    val databaseViewmodel: DatabaseNewsViewmodel = hiltViewModel()
    var value = remember {
        mutableStateOf<String?>(null)
    }
    var selected = remember {
        mutableStateOf<Filters>(Filters.Category)
    }
    LaunchedEffect(Unit) {
        value.value=selected.value.value
        newsViewModel.applyFilter(selected.value)
        snapshotFlow { value.value }
            .debounce(1000)
            .distinctUntilChanged()
            .filter {! it.isNullOrEmpty()}
            .collect {
                selected.value.value=value.value!!
                newsViewModel.applyFilter(selected.value)
            }
    }

    Column {
        FilterItems { filter ->
            selected.value = filter
            value.value = filter.value
            newsViewModel.applyFilter(selected.value)
        }
        TextField(
            value = value.value ?: "", onValueChange = { value.value = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
        NewsUiList(
            newsState.value, onItemClick = { goToDetail(it) },
            onSave = {article ->
                Log.e("article","save called")
                databaseViewmodel.saveArticles(article)
            })
    }
}

@Composable
fun FilterItems(onFilterSelected: (Filters) -> Unit = {}) {
    val selected = remember { mutableStateOf<Filters>(Filters.Category) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        filterOptions.forEach { filter ->
            val isSelected = selected.value?.displayName == filter.displayName
            Text(
                text = filter.displayName,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
                    .clickable {
                        selected.value = filter
                        onFilterSelected(filter)
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
