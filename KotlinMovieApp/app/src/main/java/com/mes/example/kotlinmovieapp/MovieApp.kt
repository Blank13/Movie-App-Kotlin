package com.mes.example.kotlinmovieapp

import android.app.Application
import android.content.Context

class MovieApp : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}