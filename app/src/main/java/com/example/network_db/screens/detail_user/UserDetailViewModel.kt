package com.example.network_db.screens.detail_user

import com.example.network_db.core.BaseViewModel
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.screens.detail_user.use_case.GetDetailUserUseCase
import com.example.network_db.screens.entity.User

class UserDetailViewModel(
    databaseRepository: DatabaseRepository,
    uuid: String,
) :
    BaseViewModel<DetailEvents, DetailState>(
        useCases = listOf(GetDetailUserUseCase(databaseRepository)),
        reducer = DetailReducer(),
        initialState = User.initialUser()
    ) {

    init {
        getUser(uuid)
    }

    private fun getUser(uuid: String) {
        handleEvent(DetailEvents.GetUser(uuid))
    }
}




