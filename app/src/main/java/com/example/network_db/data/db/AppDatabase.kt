package com.example.network_db.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.network_db.data.db.entity.UserDatabase

@Database(entities = [UserDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}