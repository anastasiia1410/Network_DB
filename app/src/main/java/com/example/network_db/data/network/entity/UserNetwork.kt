package com.example.network_db.data.network.entity

import com.example.network_db.screens.entity.User

data class UserNetwork(val name : NameNetwork)

fun UserNetwork.toUser() : User {
    return User(name = name.toName())
}

data class NameNetwork(val title : String, val first : String, val last : String)

fun NameNetwork.toName() : com.example.network_db.screens.entity.Name{
    return com.example.network_db.screens.entity.Name(
        title = title,
        first = first,
        last = last
    )
}
