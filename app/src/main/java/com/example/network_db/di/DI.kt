package com.example.network_db.di

import androidx.room.Room
import com.example.network_db.core.App
import com.example.network_db.data.db.AppDatabase
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.data.db.DatabaseRepositoryImpl
import com.example.network_db.data.network.Api
import com.example.network_db.data.network.NetworkRepository
import com.example.network_db.data.network.NetworkRepositoryImpl
import com.example.network_db.data.network.UsersPageSource
import com.example.network_db.screens.detail_user.UserDetailViewModel
import com.example.network_db.screens.detail_user.use_case.GetDetailUserUseCase
import com.example.network_db.screens.users.UserViewModel
import com.example.network_db.screens.users.use_case.GetUsersUseCase
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
    single {
        val db = Room.databaseBuilder(get(), AppDatabase::class.java, "my_table.sqlite").build()
        db.userDao()
    }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<DatabaseRepository> { DatabaseRepositoryImpl(get()) }
    single { GetUsersUseCase(get(), get()) }
    single { GetDetailUserUseCase(get()) }
    single { UsersPageSource(get()) }
    viewModel { UserViewModel(listOf(get<GetUsersUseCase>()), get()) }
    viewModel { UserDetailViewModel(listOf(get<GetDetailUserUseCase>())) }

}

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(appModule)
    }
}