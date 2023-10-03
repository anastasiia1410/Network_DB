package com.example.network_db.screens.detail_user

import com.example.network_db.screens.entity.User

sealed class DetailEvents {
    data class GetUser(val uuid : String) : DetailEvents()
    data class ShowUser(val user: User) : DetailEvents()
    data class Error(val error: String) : DetailEvents()
}
