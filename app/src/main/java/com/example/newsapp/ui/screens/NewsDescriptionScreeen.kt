package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.navigation.Destination
import com.example.newsapp.ui.commonUi.TopHeadline

@Composable
fun NewsDescriptionScreeen() {
    val navController= rememberNavController()
    Scaffold(
        topBar = {
            TopHeadline("Desc") { navController.popBackStack(Destination.TopHeadline.path, false) }
        },
    ){paddingValues ->
        WebViewScreen(modifier= Modifier.fillMaxSize().padding(paddingValues))
    }
}