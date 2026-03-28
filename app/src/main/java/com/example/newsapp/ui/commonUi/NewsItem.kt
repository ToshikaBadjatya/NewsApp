package com.example.newsapp.ui.commonUi

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.common.GlobalState
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.utils.constants.TestingSemantics


@Composable
fun NewsList(items: List<Article>, onItemClick: (Article) -> Unit = {},onSave: ((Article) -> Unit)? = null) {
    LazyColumn(modifier = Modifier.semantics{contentDescription= TestingSemantics.NEWS_LIST}) {
        items(items.size) { it ->
            NewsItem(items[it], onClick = onItemClick, onSave = onSave)
        }
    }
}

@Composable
fun NewsItem(article: Article,  onSave: ((Article) -> Unit)? = null,
             onClick: ((Article) -> Unit)? = null) {
    Log.e("item", "article shown is $article")
    val saved= remember {  mutableStateOf<Boolean>(!GlobalState.canSave(article))}
    Card(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
        onClick = {
            onClick?.invoke(article)
        }
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current).data(article.urlToImage).build(), "",
                modifier = Modifier.height(120.dp).fillMaxWidth().weight(2f)
            )
            Box(modifier = Modifier.weight(3f).padding(start = 10.dp)) {
                if (onSave!=null) {
                    IconButton(
                        onClick = {
                            saved.value=!saved.value
                            onSave.invoke(article)
                        },
                        modifier = Modifier.size(32.dp).align(Alignment.TopEnd).padding(start = 4.dp  ).semantics{
                            contentDescription= TestingSemantics.SAVED_NEWS
                        }
                    ) {
                        Icon(
                            imageVector = if (saved.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Save article",
                            tint = if (saved.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxWidth().padding(top=25.dp),) {
                    Text(article.title ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold
                        , modifier = Modifier.semantics{
                            contentDescription= TestingSemantics.NEWS_TITLE
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(article.description ?: "", maxLines = 3, overflow = TextOverflow.Clip)
                }

            }
        }
    }
}