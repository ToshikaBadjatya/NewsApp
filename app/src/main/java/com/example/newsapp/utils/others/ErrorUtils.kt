package com.example.newsapp.utils.others

import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
data class ApiError(
    val status: String?,
    val code: String?,
    val message: String?
)

fun parseError(errorBody: ResponseBody?): CustomErrorClass {
    val gson = Gson()

    val errorObj = gson.fromJson(errorBody?.string(), ApiError::class.java)

    val message = errorObj.message
    return CustomErrorClass.Unknown(message?:"")
}

fun <T> Response<T>.toCustomError(): CustomErrorClass {
    return when (code()) {
        401, 403 -> CustomErrorClass.Unauthorized
        404      -> CustomErrorClass.NotFound
        408      -> CustomErrorClass.Timeout
        429      -> CustomErrorClass.RateLimited
        in 500..599 -> CustomErrorClass.ServerError
        else     -> parseError(errorBody())
    }
}

sealed class CustomErrorClass(val msg: String) : Throwable(msg) {
    object NoInternet  : CustomErrorClass("No internet connection. Please check your network.")
    object Timeout     : CustomErrorClass("Request timed out. Please try again.")
    object Unauthorized: CustomErrorClass("Session expired. Please login again.")
    object NotFound    : CustomErrorClass("Requested resource not found.")
    object ParsingError: CustomErrorClass("Something went wrong while processing data.")
    object RateLimited : CustomErrorClass("Too many requests. Please slow down.")
    object ServerError : CustomErrorClass("Server error. Please try again later.")
    class  Unknown(msg: String)    : CustomErrorClass(msg)
}
