package com.example.network_db.screens.list

import com.example.network_db.core.BaseViewModel
import com.example.network_db.core.UseCase
import com.example.network_db.screens.entity.User

class UserViewModel(useCase: List<UseCase<UserEvents, UserState>>) :
    BaseViewModel<UserEvents, UserState>(
        useCases = useCase,
        reducer = UserReducer(),
        initialState = User.initial()
    ) {

    fun getList() {
        handleEvent(UserEvents.GetList)
    }
}