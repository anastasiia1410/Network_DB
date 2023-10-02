package com.example.network_db.screens.list

import com.example.network_db.core.BaseViewModel
import com.example.network_db.data.network.NetworkManager
import com.example.network_db.screens.entity.User
import com.example.network_db.screens.list.use_case.ShowListUseCase

class UserViewModel(networkManager: NetworkManager) : BaseViewModel<UserEvents, UserState>(
    useCases = listOf(ShowListUseCase(networkManager)),
    reducer = UserReducer(),
    initialState = User.initial()
) {

    fun getList(){
        handleEvent(UserEvents.GetList)
    }
}