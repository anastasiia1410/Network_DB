package com.example.network_db.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network_db.data.db.DatabaseRepository
import com.example.network_db.data.network.entity.toUser
import com.example.network_db.screens.entity.User

class UsersPageSource(private val api: Api, private val databaseRepository: DatabaseRepository) :
    PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val results: Int = params.loadSize
            val users = api.getUsers(page, results).userList.map { it.toUser() }
            if (page == 1) {
                databaseRepository.clearTable()
            }
            databaseRepository.insert(users)
            val nextKey = if (users.size < results) null else page.plus(1)
            val prevKey = if (page == 1) null else page.minus(1)
            LoadResult.Page(users, prevKey, nextKey)
        } catch (e: Exception) {
            return if (params.key != 1) {
                LoadResult.Page(emptyList(), null, null)
            } else {
                val usersFromDb = databaseRepository.getUsers()
                LoadResult.Page(usersFromDb, null, null)
            }
        }
    }
}
