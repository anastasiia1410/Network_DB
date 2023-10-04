package com.example.network_db.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network_db.data.network.entity.toUser
import com.example.network_db.screens.entity.User

class UsersPageSource(private val api: Api) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page: Int = params.key ?: 1
        val results: Int = params.loadSize
        val response = api.getUsers(page, results)
        val users = response.userList.map { it.toUser() }
        val nextKey = if (users.size < results) null else page.plus(1)
        val prevKey = if (page == 1) null else page.minus(1)
        return LoadResult.Page(users, prevKey, nextKey)
    }
}