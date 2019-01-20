package com.mes.example.kotlinmovieapp

import android.app.Application
import io.realm.Realm

class MovieApp : Application() {

    override fun onCreate() {
        Realm.init(applicationContext)
        super.onCreate()
    }
}