package com.example.network_db.screens.detail_user

import com.example.network_db.core.BaseViewModel
import com.example.network_db.core.UseCase
import com.example.network_db.screens.entity.User

class UserDetailViewModel(useCase: List<UseCase<DetailEvents, DetailState>>, uuid : String) :
    BaseViewModel<DetailEvents, DetailState>(
        useCases = useCase,
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