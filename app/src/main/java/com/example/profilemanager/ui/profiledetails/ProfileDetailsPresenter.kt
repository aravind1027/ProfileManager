package com.example.profilemanager.ui.profiledetails

import android.app.Application
import com.example.profilemanager.database.User
import com.example.profilemanager.database.UserRepository
import com.example.profilemanager.database.UserRoomDatabase

class ProfileDetailsPresenter(
    private var view: ProfileDetailsContract.View,
    application: Application
) : ProfileDetailsContract.Presenter {

    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    override fun getUserByName(firstName: String, lastName: String): User {
        return repository.findByName(firstName, lastName)
    }

    override fun displayProfile(user: User) {
        val (firstName, lastName, age, dateOfBirth, gender, educationLevel, profilePicture, bio) = user
        view.setPersonalInfo(firstName, lastName, age, dateOfBirth, gender, educationLevel)
        view.setProfilePicture(profilePicture)
        view.setBriefDescription(bio)
    }
}