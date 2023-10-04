package com.example.network_db.screens.users

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.network_db.core.BaseViewModel
import com.example.network_db.core.UseCase
import com.example.network_db.data.network.Api.Companion.DEFAULT_PAGE_SIZE
import com.example.network_db.data.network.UsersPageSource
import com.example.network_db.screens.entity.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel(
    useCase: List<UseCase<UserEvents, UserState>>,
    private val pagingSource: UsersPageSource,
) :
    BaseViewModel<UserEvents, UserState>(
        useCases = useCase,
        reducer = UserReducer(),
        initialState = User.initialUsers()
    ) {

    val users: StateFlow<PagingData<User>> = Pager(config = PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = 2
        ),
        pagingSourceFactory = {
            pagingSource
        }, initialKey = 1
    ).flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun getList() {
        handleEvent(UserEvents.GetList)
    }
}