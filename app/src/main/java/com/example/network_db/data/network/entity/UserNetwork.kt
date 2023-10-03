package com.example.network_db.data.network.entity

import com.example.network_db.screens.entity.User
import com.google.gson.annotations.SerializedName

data class UserNetwork(
    @SerializedName("name")
    val name: NameNetwork,
    @SerializedName("login")
    val login: LoginNetwork,
)

fun UserNetwork.toUser(): User {
    return User(
        uuid = login.uuid,
        title = name.title,
        firstName = name.firstName,
        lastName = name.lastName
    )
}

data class NameNetwork(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String)

data class LoginNetwork( @SerializedName("uuid") val uuid: String)