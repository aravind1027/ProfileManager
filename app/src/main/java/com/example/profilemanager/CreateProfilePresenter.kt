package com.example.profilemanager

import android.app.Application
import android.util.Log

class CreateProfilePresenter(
    private var view: CreateProfileContract.View?,
    application: Application
) : CreateProfileContract.Presenter {
    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    override fun addUser(user: User) {
        repository.insert(user)
    }

    override fun onDestroy() {
        view = null
    }
}