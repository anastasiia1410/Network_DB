package com.example.network_db.data.network.entity.reponse

import com.example.network_db.data.network.entity.UserNetwork
import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("results") val userList: List<UserNetwork>)