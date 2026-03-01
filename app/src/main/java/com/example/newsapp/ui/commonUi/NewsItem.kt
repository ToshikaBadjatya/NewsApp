package com.example.newsapp.ui.commonUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.example.newsapp.di.modifier

@Composable
fun NewsItem() {
    Card(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
        onClick = {}) {
        Row(modifier= Modifier.padding(8.dp)) {
            Icon(imageVector = Icons.Default.AccountCircle,"",
                modifier= Modifier.height(120.dp).fillMaxWidth().weight(2f))
            Column( modifier= Modifier.wrapContentHeight().fillMaxWidth().weight(3f).padding(start = 10.dp)) {
                Text("title")
                Text("cdsvsgsfgfdgdg")
            }
        }
    }


}