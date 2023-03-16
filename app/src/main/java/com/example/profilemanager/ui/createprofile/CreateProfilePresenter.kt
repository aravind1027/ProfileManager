package com.example.profilemanager.ui.createprofile

import android.app.Application
import com.example.profilemanager.database.User
import com.example.profilemanager.database.UserRepository
import com.example.profilemanager.database.UserRoomDatabase

class CreateProfilePresenter(
    view: CreateProfileContract.View,
    application: Application
) : CreateProfileContract.Presenter {
    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    override fun addUser(user: User) {
        repository.insert(user)
    }
}