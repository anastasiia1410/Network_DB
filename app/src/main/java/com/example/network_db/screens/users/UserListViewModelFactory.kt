package com.example.network_db.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.network_db.data.network.UsersPageSource

class UserListViewModelFactory(private val pagingSource: UsersPageSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(pagingSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
