package com.example.newsapp.interfaces

import android.util.Log

interface Logger {
    fun log(tag: String,msg: String)
}
class AppLogger: Logger{
    override fun log(tag: String, msg: String) {
       Log.e(tag,msg)
    }

}
class TestLogger: Logger{
    override fun log(tag: String, msg: String) {
       //no test implementation
    }

}