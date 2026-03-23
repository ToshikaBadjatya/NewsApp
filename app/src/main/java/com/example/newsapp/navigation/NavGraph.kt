package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.FilterScreen
import com.example.newsapp.ui.screens.NewsDescriptionScreeen
import com.example.newsapp.ui.screens.SavedItemsScreen
import com.example.newsapp.ui.screens.SearchScreen
import com.example.newsapp.ui.screens.TopHeadlineScreen
import java.net.URLDecoder

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destination.TopHeadline.path,
        modifier = modifier,
    ) {
        composable(Destination.TopHeadline.path) {
            TopHeadlineScreen(navController)
        }
        composable(Destination.Search.path) {
            SearchScreen() {
                navController.navigate("news_detail")
        }
        }
        composable(Destination.Saved.path) {
            SavedItemsScreen(navController)
        }
        composable(Destination.FilterScreen.path) {
            FilterScreen(){
                navController.navigate("news_detail") }
            }
        }


}
