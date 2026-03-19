package com.example.newsapp.utils.others

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Response


fun parseError(errorBody: ResponseBody?): CustomErrorClass {
    Log.e("errorCheck","eerorBody ${errorBody}")
    val errorString = errorBody?.string()

    Log.e("errorCheck", "errorBody: $errorString")

    return CustomErrorClass.NoInternet
}



sealed class CustomErrorClass(val msg: String) : Exception(msg) {
    object NoInternet : CustomErrorClass("No internet connection. Please check your network.")

    object Timeout : CustomErrorClass("Request timed out. Please try again.")

    object Unauthorized :
        CustomErrorClass("Session expired. Please login again.")

    object NotFound :
        CustomErrorClass("Requested resource not found.")

    object ParsingError :
        CustomErrorClass("Something went wrong while processing data.")

    object Unknown :
        CustomErrorClass("Something went wrong. Please try again.")
}