package com.example.network_db.screens.users.use_case

import com.example.network_db.core.UseCase
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.data.network.NetworkRepository
import com.example.network_db.screens.users.UserEvents
import com.example.network_db.screens.users.UserState

class GetUsersUseCase(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
) : UseCase<UserEvents, UserState> {
    override fun canHandle(event: UserEvents): Boolean {
        return event is UserEvents.GetList
    }

    override suspend fun invoke(event: UserEvents, state: UserState): UserEvents {
        return (event as? UserEvents.GetList)?.let {
            try {
                val netUserList = networkRepository.getUsers()
                databaseRepository.clearTable()
                databaseRepository.insert(netUserList)
                return UserEvents.ShowList(netUserList)
            } catch (e: Exception) {
                val dbUserList = databaseRepository.getUsers()
                return UserEvents.ShowList(dbUserList)
            }
        } ?: UserEvents.Error("Wrong event type : $event")
    }
}