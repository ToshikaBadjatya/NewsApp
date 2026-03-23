package com.example.newsapp.utils.constants

object NetworkConstants {
    const val API_KEY="28605d2ed69a4ee096ef21a3859164b2"
    const val BASE_URL="https://newsapi.org/v2/"
    const val TOP_HEADLINES=BASE_URL+"/top-headlines"
    const val EVERYTHING=BASE_URL+"/everything"
    const val DEFAULT_PAGE_SIZE=10
    const val DEFAULT_COUNTRY= "us"
    const val DEFAULT_LANGUAGE= "en"
    const val DEFAULT_CATEGORY= "technology"
    const val DEFAULT_PAGE_NUM=1
    const val READ_TIMEOUT=4000L
    const val WRITE_TIMEOUT=4000
}