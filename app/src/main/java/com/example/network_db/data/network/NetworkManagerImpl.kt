package com.example.network_db.data.network

import com.example.network_db.data.network.entity.toUser
import com.example.network_db.screens.entity.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManagerImpl : NetworkManager {
    private val gson: Gson
    private val client: OkHttpClient
    private val retrofit: Retrofit
    private val api: Api

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        gson = GsonBuilder().serializeNulls().create()
        retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }

    override suspend fun getUsers(): List<User> {
        return api.getUsers().userList.map {
            it.toUser()
        }
    }
}