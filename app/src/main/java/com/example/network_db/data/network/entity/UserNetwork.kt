package com.example.network_db.data.network.entity

import com.example.network_db.screens.entity.Login
import com.example.network_db.screens.entity.User

data class UserNetwork(val name: NameNetwork, val login: LoginNetwork)

fun UserNetwork.toUser(): User {
    return User(
        name = name.toName(),
        login = login.toLogin()
    )
}

data class NameNetwork(val title: String, val first: String, val last: String)

fun NameNetwork.toName(): com.example.network_db.screens.entity.Name {
    return com.example.network_db.screens.entity.Name(
        title = title,
        first = first,
        last = last
    )
}

data class LoginNetwork(val uuid: String)

fun LoginNetwork.toLogin(): Login {
    return Login(uuid = uuid)
}
