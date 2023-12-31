package com.example.network_db.data.db

import com.example.network_db.data.db.entity.toUser
import com.example.network_db.data.db.entity.toUserDatabase
import com.example.network_db.screens.entity.User

class DatabaseRepositoryImpl(private val userDao: UserDao) : DatabaseRepository {
    override suspend fun insert(users: List<User>) {
        userDao.insert(users.map { it.toUserDatabase() })
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getUsers().map { it.toUser() }
    }

    override suspend fun getUserById(uuid: String): User {
        return userDao.getUserById(uuid).toUser()
    }

    override suspend fun clearTable() {
        userDao.clearTable()
    }
}