package com.example.newsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Destination(open val path: String,
                         open val label: String,
                         open val icon: ImageVector, open val order:Int) {
    object TopHeadline : Destination(
        path = "top_headline",
        label = "Top News",
        icon = Icons.Default.Home,
        order = 0

    )
    object Search : Destination(
        path = "search_screen",
        label = "Search",
        icon = Icons.Default.Search,
        order = 1
    )

    object Saved : Destination(
        path = "saved_screen",
        label = "Saved",
        icon = Icons.Default.Favorite,
        order = 2
    )



    object FilterScreen : Destination(
        path = "filter_screen",
        label = "Filters",
        icon = Icons.Default.Favorite,
        order = 3
    )


    }

sealed class MainDestinations(open val path: String,
                      ) {

    object NewsDetail : MainDestinations(
        path = "news_detail/{url}",
    ) {
        fun createRoute(url: String) = "news_detail/${java.net.URLEncoder.encode(url, "UTF-8")}"
    }
    object Dashboard : MainDestinations(
        path = "dashboard",

    )
}
