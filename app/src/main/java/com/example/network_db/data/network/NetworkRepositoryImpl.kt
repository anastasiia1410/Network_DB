package com.example.network_db.data.network

import com.example.network_db.data.network.entity.toUser
import com.example.network_db.screens.entity.User

class NetworkRepositoryImpl(private val api: Api) : NetworkRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().userList.map {
            it.toUser()
        }
    }
}