package com.example.newsapp.ui.commonUi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.data.remote.models.Article

@Composable
fun NewsItem(article: Article) {
    Log.e("item","article shown is $article")
    Card(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
        onClick = {}) {
        Row(modifier= Modifier.padding(8.dp)) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .build(),"",
                modifier= Modifier.height(120.dp).fillMaxWidth().weight(2f))
            Column( modifier= Modifier.wrapContentHeight().fillMaxWidth().weight(3f).padding(start = 10.dp)) {
                Text(article.title?:"", maxLines = 1,overflow= TextOverflow.Ellipsis, fontWeight = FontWeight.Bold )
                Spacer(modifier = Modifier.height(20.dp))
                Text(article.description?:"", maxLines = 3,overflow= TextOverflow.Ellipsis)
            }
        }
    }


}