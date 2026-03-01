package com.example.newsapp.ui.screens

import android.annotation.SuppressLint

import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsapp.di.modifier
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import okio.Timeout

@Composable
fun WebViewScreen( timeout: Timeout  ) {
    AndroidView({context->
        TextView(
            context
        )
    }, modifier= Modifier.fillMaxSize())
}

@Composable
 fun getMessageFromInternet() {
     val liststate= rememberLazyListState()
    val enabled by remember{ derivedStateOf { liststate.firstVisibleItemIndex>20 } }
    LazyColumn (state=liststate,){
        items((1..10).toList()) {
            Text("dscf")
        }
    }
  Button({},enabled=enabled){
        Text("cdsfd")
    }
}
