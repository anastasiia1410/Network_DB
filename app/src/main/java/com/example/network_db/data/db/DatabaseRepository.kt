package com.example.network_db.data.db

import com.example.network_db.screens.entity.User
interface DatabaseRepository {
    suspend fun insert(users: List<User>)

    suspend fun getUsers(): List<User>

    suspend fun getUserById(uuid: String) : User

    suspend fun clearTable()

}