package com.example.network_db.screens.list

import com.example.network_db.screens.entity.User

sealed class UserEvents {

    data object GetList : UserEvents()
    data class ShowList(val userList: List<User>) : UserEvents()
    data class Error(val error: String) : UserEvents()
}