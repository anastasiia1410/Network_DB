package com.example.network_db.screens.detail_user

import com.example.network_db.core.Reducer

class DetailReducer : Reducer<DetailEvents, DetailState> {
    override fun reduce(event: DetailEvents, state: DetailState): DetailState {
        return when (event) {
            is DetailEvents.GetUser, is DetailEvents.Error -> state
            is DetailEvents.ShowUser -> state.copy(user = event.user)
        }
    }
}