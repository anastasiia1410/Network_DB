package com.example.network_db.screens.list

import com.example.network_db.core.Reducer

class UserReducer : Reducer<UserEvents, UserState> {
    override fun reduce(event: UserEvents, state: UserState): UserState {
        return when (event) {
            is UserEvents.Error -> state
            UserEvents.GetList -> state
            is UserEvents.ShowList -> state.copy(userList = event.userList)
        }
    }
}