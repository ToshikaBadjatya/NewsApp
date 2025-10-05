package com.example.newsapp.navigation

sealed class Destination(val path: String) {
    object  Search : Destination("search_screen")

    object Saved : Destination("saved_screen")

    object TopHeadline : Destination("top_headline")

    data class FilterScreen(val category: String, val country: String) : Destination("filter_screen")
}