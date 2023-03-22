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

    override fun displayProfile(userId: Int) {
        if (userId >= 0) {
            val user = repository.findById(userId)
            val (firstName, lastName, dateOfBirth, gender, educationLevel, hobbies, profilePicture, bio) = user
            view.setPersonalInfo(
                firstName,
                lastName,
                user.calculateAge(),
                dateOfBirth,
                gender,
                educationLevel,
                hobbies
            )
            view.setProfilePicture(profilePicture)
            view.setBriefDescription(bio)
        }
    }
}