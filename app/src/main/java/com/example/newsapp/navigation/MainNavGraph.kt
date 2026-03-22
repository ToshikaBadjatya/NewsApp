package com.example.newsapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.MainLauncherScreen
import com.example.newsapp.ui.screens.NewsDescriptionScreeen
import java.net.URLDecoder

@Composable
fun MainNavGraph() {
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainDestinations.Dashboard.path,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(
            route = MainDestinations.Dashboard.path,
        ) {
            MainLauncherScreen()
        }

        composable(
            route = MainDestinations.NewsDetail.path,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = URLDecoder.decode(backStackEntry.arguments?.getString("url") ?: "", "UTF-8")
            NewsDescriptionScreeen(navController = navController, url = url)
        }
    }
}
