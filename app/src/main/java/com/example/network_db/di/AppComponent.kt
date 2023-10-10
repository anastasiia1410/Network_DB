package com.example.network_db.di

import com.example.network_db.screens.detail_user.UserDetailFragment
import com.example.network_db.screens.main.MainActivity
import com.example.network_db.screens.users.UsersListFragment
import dagger.Component

@Component(modules = [DataModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun injectUsersListFragment(usersListFragment: UsersListFragment)
    fun injectUserDetailFragment(userDetailFragment: UserDetailFragment)

}