package com.example.network_db.screens.entity

import com.example.network_db.screens.list.UserState

data class User(val name : Name){
    companion object{
        fun initial() : UserState{
            return UserState(userList = emptyList())
        }
    }
}