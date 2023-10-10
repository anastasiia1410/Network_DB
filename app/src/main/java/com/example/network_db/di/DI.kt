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
import com.example.network_db.screens.detail_user.UserDetailViewModelFactory
import com.example.network_db.screens.detail_user.use_case.GetDetailUserUseCase
import com.example.network_db.screens.users.UserViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_table.sqlite").build()
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUsersPageSource(api: Api, databaseRepository: DatabaseRepository): UsersPageSource {
        return UsersPageSource(api, databaseRepository)
    }

    @Provides
    fun provideDatabaseRepository(impl: DatabaseRepositoryImpl): DatabaseRepository {
        return impl
    }

    @Provides
    fun provideNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository {
        return impl
    }
}

@Module
class UseCaseModule {
    @Provides
    fun provideGetDetailUserUseCase(databaseRepository: DatabaseRepository): GetDetailUserUseCase {
        return GetDetailUserUseCase(databaseRepository)
    }
}

@Module
class ViewModelModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideFactory(
        databaseRepository: DatabaseRepository,
        uuid: String,
    ): UserDetailViewModelFactory {
        return UserDetailViewModelFactory(databaseRepository, uuid)
    }

    @Provides
    fun provideUserViewModel(usersPageSource: UsersPageSource): UserViewModel {
        return UserViewModel(usersPageSource)
    }
}
