package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.ui.screens.FilterScreen
import com.example.newsapp.ui.screens.SavedItemsScreen
import com.example.newsapp.ui.screens.SearchScreen
import com.example.newsapp.ui.screens.TopHeadlineScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier, goToDetail: (Article) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Destination.TopHeadline.path,
        modifier = modifier,
    ) {
        composable(Destination.TopHeadline.path) {
            TopHeadlineScreen(goToDetail)
        }
        composable(Destination.Search.path) {
            SearchScreen(goToDetail)
        }
        composable(Destination.Saved.path) {
            SavedItemsScreen(goToDetail)
        }
        composable(Destination.FilterScreen.path) {
            FilterScreen(goToDetail)
        }
    }


}
