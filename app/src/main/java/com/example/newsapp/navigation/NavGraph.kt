package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph( navController: NavHostController,modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destination.TopHeadline.path,
        modifier = modifier,
    ){
        composable(Destination.TopHeadline.path) {  }
        composable(Destination.Search.path) {  }
       composable(Destination.Saved.path) {  }
       composable(Destination.FilterScreen.path) {  }
    }

}