package com.example.network_db.data.network

import com.example.network_db.data.network.entity.reponse.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = DEFAULT_PAGE_SIZE,
    ): Response

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}