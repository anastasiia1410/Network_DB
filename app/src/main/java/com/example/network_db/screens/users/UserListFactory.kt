package com.example.network_db.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.network_db.data.network.UsersPageSource

class UserListFactory(private val pageSource: UsersPageSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(pageSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
