package com.example.network_db.screens.detail_user.use_case

import com.example.network_db.core.UseCase
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.screens.detail_user.DetailEvents
import com.example.network_db.screens.detail_user.DetailState
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvents, DetailState> {
    override fun canHandle(event: DetailEvents): Boolean {
        return event is DetailEvents.GetUser
    }

    override suspend fun invoke(event: DetailEvents, state: DetailState): DetailEvents {
        return ((event as? DetailEvents.GetUser)?.let {
            val user = databaseRepository.getUserById(event.uuid)
            return DetailEvents.ShowUser(user)
        } ?: DetailEvents.Error("Wrong event type : $event"))
    }
}