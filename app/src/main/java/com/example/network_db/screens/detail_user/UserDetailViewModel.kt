package com.example.network_db.screens.detail_user

import com.example.network_db.core.BaseViewModel
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.screens.detail_user.use_case.GetDetailUserUseCase
import com.example.network_db.screens.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(databaseRepository: DatabaseRepository) :
    BaseViewModel<DetailEvents, DetailState>(
        useCases = listOf(GetDetailUserUseCase(databaseRepository)),
        reducer = DetailReducer(),
        initialState = User.initialUser()
    ) {


    fun getUser(uuid: String) {
        handleEvent(DetailEvents.GetUser(uuid))
    }
}