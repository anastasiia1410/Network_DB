package com.example.network_db.data.network.entity

import com.example.network_db.screens.entity.User

data class UserNetwork(val name: NameNetwork, val login: LoginNetwork)

fun UserNetwork.toUser(): User {
    return User(
        uuid = login.uuid,
        title = name.title,
        firstName = name.firstName,
        lastName = name.lastName
    )
}

data class NameNetwork(val title: String, val firstName: String, val lastName: String)

data class LoginNetwork(val uuid: String)