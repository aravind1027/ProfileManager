package com.example.profilemanager.ui.allprofiles

import android.app.Application
import com.example.profilemanager.database.User
import com.example.profilemanager.database.UserRepository
import com.example.profilemanager.database.UserRoomDatabase

class AllProfilesPresenter(
    private var view: AllProfilesContract.View?,
    application: Application
) : AllProfilesContract.Presenter {

    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    override fun getAllUsers(): MutableList<User> {
        return repository.getAll()
    }

    override fun addUser(user: User) {
        repository.insert(user)
    }

    override fun removeUser(user: User) {
        repository.delete(user)
    }
}