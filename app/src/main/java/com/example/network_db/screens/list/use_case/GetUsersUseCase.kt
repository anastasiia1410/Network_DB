package com.example.network_db.screens.list.use_case

import com.example.network_db.core.UseCase
import com.example.network_db.data.network.NetworkRepository
import com.example.network_db.screens.list.UserEvents
import com.example.network_db.screens.list.UserState

class GetUsersUseCase(private val networkRepository: NetworkRepository) : UseCase<UserEvents, UserState> {
    override fun canHandle(event: UserEvents): Boolean {
        return event is UserEvents.GetList
    }

    override suspend fun invoke(event: UserEvents, state: UserState): UserEvents {
        return (event as? UserEvents.GetList)?.let {
            val userList = networkRepository.getUsers()
            return UserEvents.ShowList(userList)
        } ?: UserEvents.Error("Wrong event type : $event")
    }
}