package com.example.network_db.data.network

import com.example.network_db.data.network.entity.reponse.Response
import retrofit2.http.GET

interface Api {

    @GET
    suspend fun getUsers() : Response
}