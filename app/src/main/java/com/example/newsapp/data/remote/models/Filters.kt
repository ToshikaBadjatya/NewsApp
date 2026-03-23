package com.example.newsapp.data.remote.models

sealed class Filters(val displayName: String) {
     var value: String=""

    object Language : Filters(displayName = "Language")
    object Country : Filters(displayName = "Country")

    object Source : Filters(displayName = "Source")

}