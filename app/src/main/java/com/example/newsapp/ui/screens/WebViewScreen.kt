package com.example.newsapp.ui.screens

import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(modifier: Modifier) {
    AndroidView({context->
        TextView(
            context
        )
    }, modifier= modifier)
}


