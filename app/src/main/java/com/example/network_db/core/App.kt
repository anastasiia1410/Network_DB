package com.example.network_db.core

import android.app.Application
import android.content.Context
import com.example.network_db.di.AppComponent
import com.example.network_db.di.DaggerAppComponent
import com.example.network_db.di.ViewModelModule

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerAppComponent
                .builder()
                .viewModelModule(ViewModelModule(this))
                .build()
    }

    companion object {
        fun getInstance(context: Context): App = context.applicationContext as App
    }
}