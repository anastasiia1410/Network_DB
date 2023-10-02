package com.example.network_db.di

import com.example.network_db.core.App
import com.example.network_db.data.network.Api
import com.example.network_db.data.network.NetworkRepository
import com.example.network_db.data.network.NetworkRepositoryImpl
import com.example.network_db.screens.list.UserViewModel
import com.example.network_db.screens.list.use_case.ShowListUseCase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val appModule = module {
    single { GsonBuilder().serializeNulls().create() }
    single {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single { get<Retrofit>().create(Api::class.java) }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    viewModel { UserViewModel(listOf(ShowListUseCase(get()))) }
}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(appModule)
    }
}