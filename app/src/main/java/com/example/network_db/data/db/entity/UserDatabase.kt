package com.example.network_db.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.network_db.screens.entity.User

@Entity(tableName = "User")
data class UserDatabase(
    @PrimaryKey(autoGenerate = false)
    val uuid: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
)

fun User.toUserDatabase(): UserDatabase {
    return UserDatabase(
        uuid = uuid,
        title = title,
        firstName = firstName,
        lastName = lastName
    )
}

fun UserDatabase.toUser(): User {
    return User(
        uuid = uuid,
        title = title,
        firstName = firstName,
        lastName = lastName
    )
}


