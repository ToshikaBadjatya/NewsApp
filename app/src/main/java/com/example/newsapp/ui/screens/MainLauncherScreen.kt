package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.navigation.Destination
import com.example.newsapp.navigation.NavGraph
import com.example.newsapp.ui.commonUi.TopHeadline
import com.example.newsapp.ui.commonUi.BottomNavigationBar

@Composable
fun MainLauncherScreen( goToDetail:(Article)-> Unit){
    val navController= rememberNavController()
    val selectedPosition = remember {
        mutableStateOf(0)
    }
    val selectedDestination = remember {
        mutableStateOf<Destination>(Destination.TopHeadline)
    }
    Scaffold(
        topBar = {
            TopHeadline(selectedDestination.value.label) { navController.popBackStack(Destination.TopHeadline.path, false) }
        },
        bottomBar = {
            BottomNavigationBar(selectedPosition.value){index,destination->
                selectedPosition.value=index
                selectedDestination.value=destination
                goToScreen(destination,navController)
            }
        }
    ) {paddingValues ->
        NavGraph(navController, modifier = Modifier.fillMaxSize().padding(paddingValues),goToDetail)
    }

}

fun goToScreen(destination: Destination, navController: NavHostController) {
    navController.navigate(destination.path){
        popUpTo(Destination.TopHeadline.path)
        launchSingleTop=true
    }

}
