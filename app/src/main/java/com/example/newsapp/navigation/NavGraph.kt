package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.screens.FilterScreen
import com.example.newsapp.ui.screens.NewsDescriptionScreeen
import com.example.newsapp.ui.screens.SavedItemsScreen
import com.example.newsapp.ui.screens.SearchScreen
import com.example.newsapp.ui.screens.TopHeadlineScreen

@Composable
fun NavGraph( navController: NavHostController,modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destination.Search.path,
        modifier = modifier,
    ){
//        composable(Destination.TopHeadline.path) {
//            TopHeadlineScreen()
//        }
        composable(Destination.Search.path) {
            SearchScreen()
        }
       composable(Destination.Saved.path) {
           SavedItemsScreen()
       }
       composable(Destination.FilterScreen.path) {
           FilterScreen()
       }
        composable("news_detail") {
            NewsDescriptionScreeen()
        }
    }

}