package com.example.profilemanager

import android.app.Application

class AllProfilesPresenter(
    private var view: AllProfilesContract.View?,
    application: Application
) : AllProfilesContract.Presenter {

    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    override fun getAllUsers(): List<User> {
        return repository.getAll()
    }

    override fun onDestroy() {
        view = null
    }
}