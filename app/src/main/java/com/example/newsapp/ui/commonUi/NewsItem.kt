package com.example.newsapp.ui.commonUi

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.data.remote.models.Article

@Composable
fun NewsList(items: List<Article>) {
    LazyColumn {
        items(items.size){it->
            NewsItem(items[it])
        }
    }
}

@Composable
fun NewsItem(article: Article, showSave:Boolean=true,onSave: ((Article) -> Unit)? = null) {
    Log.e("item", "article shown is $article")
    val saved = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
        onClick = {}
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current).data(article.urlToImage).build(), "",
                modifier = Modifier.height(120.dp).fillMaxWidth().weight(2f),
            )
            Column(
                modifier = Modifier.wrapContentHeight().fillMaxWidth().weight(3f).padding(start = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if(showSave){
                    Image(
                            painter = if (saved.value) painterResource(R.drawable.ic_save) else painterResource(R.drawable.ic_save),
                            contentDescription = "Save article",
                            modifier= Modifier.clickable{

                                    saved.value = !saved.value
                                    onSave?.invoke(article)

                            }, alignment = Alignment.TopEnd
                        )

                }

                Text(article.title ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(article.description ?: "", maxLines = 3, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}