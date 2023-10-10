package com.example.network_db.screens.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.network_db.data.db.DatabaseRepository

class UserDetailViewModelFactory(val databaseRepository: DatabaseRepository, val uuid: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(databaseRepository, uuid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
