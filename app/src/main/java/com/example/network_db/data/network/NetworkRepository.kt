package com.example.network_db.data.network

import com.example.network_db.screens.entity.User

interface NetworkRepository {

   suspend  fun getUsers() : List<User>
}