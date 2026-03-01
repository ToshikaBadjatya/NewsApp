package com.example.newsapp.ui.commonUi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.newsapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadline(goback:()-> Unit) {
    val context= LocalContext.current
    TopAppBar(title = {Text(context.getString(R.string.app_name))},
        navigationIcon = { IconButton({goback}){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
            )
        }
        },
        colors = TopAppBarDefaults.topAppBarColors(   containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,)
        )
}