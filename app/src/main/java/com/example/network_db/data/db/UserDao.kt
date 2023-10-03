package com.example.network_db.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.network_db.data.db.entity.UserDatabase

@Dao
interface UserDao {
    @Insert
    suspend fun insert(users: List<UserDatabase>)

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<UserDatabase>

    @Query("DELETE FROM User")
    suspend fun clearTable()
}