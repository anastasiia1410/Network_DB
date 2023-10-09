package com.example.network_db.di

import android.content.Context
import androidx.room.Room
import com.example.network_db.data.db.AppDatabase
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.data.db.DatabaseRepositoryImpl
import com.example.network_db.data.db.UserDao
import com.example.network_db.data.network.Api
import com.example.network_db.data.network.NetworkRepository
import com.example.network_db.data.network.NetworkRepositoryImpl
import com.example.network_db.data.network.UsersPageSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideDb(@ApplicationContext context: Context): UserDao {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "my_table.sqlite").build()
        return db.userDao()
    }

    @Provides
    fun provideNetworkRepository(api: Api): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }

    @Provides
    fun initDatabaseRepository(userDao: UserDao): DatabaseRepository {
        return DatabaseRepositoryImpl(userDao)
    }

    @Provides
    fun provideUsersPageSource(api: Api, databaseRepository: DatabaseRepository): UsersPageSource {
        return UsersPageSource(api, databaseRepository)

    }
}

