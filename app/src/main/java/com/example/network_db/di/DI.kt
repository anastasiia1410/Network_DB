package com.example.network_db.di

import com.example.network_db.core.App
import com.example.network_db.data.network.NetworkManager
import com.example.network_db.data.network.NetworkManagerImpl
import com.example.network_db.screens.list.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val appModule = module {
    single<NetworkManager> { NetworkManagerImpl() }
    viewModel { UserViewModel(get()) }
}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(appModule)
    }
}