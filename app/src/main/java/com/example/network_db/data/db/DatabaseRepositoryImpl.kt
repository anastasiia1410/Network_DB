package com.example.network_db.data.db

import com.example.network_db.data.db.entity.toUser
import com.example.network_db.data.db.entity.toUserDatabase
import com.example.network_db.screens.entity.User

class DatabaseRepositoryImpl(private val db: AppDatabase) : DatabaseRepository {
    override suspend fun insert(users: List<User>) {
        db.userDao().insert(users.map { it.toUserDatabase() })
    }

    override suspend fun getUsers(): List<User> {
        return db.userDao().getUsers().map { it.toUser() }
    }

    override suspend fun clearTable(users: List<User>) {
        db.userDao().clearTable(users.map { it.toUserDatabase() })
    }
}