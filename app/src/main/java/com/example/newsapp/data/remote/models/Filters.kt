package com.example.newsapp.data.remote.models

import com.example.newsapp.utils.constants.NetworkConstants

sealed class Filters(val displayName: String,var value: String) {


    object Language : Filters(displayName = "Language", NetworkConstants.DEFAULT_LANGUAGE)
    object Country : Filters(displayName = "Country", NetworkConstants.DEFAULT_COUNTRY)

    object Category : Filters(displayName = "Category", NetworkConstants.DEFAULT_CATEGORY)

}