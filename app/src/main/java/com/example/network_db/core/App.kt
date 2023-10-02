package com.example.network_db.core

import android.app.Application
import com.example.network_db.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}