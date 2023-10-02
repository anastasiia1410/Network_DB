package com.example.network_db.screens.list.use_case

import com.example.network_db.core.UseCase
import com.example.network_db.data.network.NetworkManager
import com.example.network_db.screens.entity.User
import com.example.network_db.screens.list.UserEvents
import com.example.network_db.screens.list.UserState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShowListUseCase(private val networkManager: NetworkManager) : UseCase<UserEvents, UserState>,
    CoroutineScope {
    override fun canHandle(event: UserEvents): Boolean {
        return event is UserEvents.GetList
    }

    override suspend fun invoke(event: UserEvents, state: UserState): UserEvents {
        return (event as? UserEvents.GetList)?.let {
            val userList = networkManager.getUsers()
            return UserEvents.ShowList(userList)
        } ?: UserEvents.Error("Wrong event type : $event")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}