package com.example.network_db.screens.entity

import com.example.network_db.screens.list.UserState

data class User(
    val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
) {
    companion object {
        fun initial(): UserState {
            return UserState(userList = emptyList())
        }
    }
}