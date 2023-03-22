package com.example.profilemanager.ui.profiledetails

import com.example.profilemanager.database.User

interface ProfileDetailsContract {
    interface View {
        fun setPersonalInfo(firstName: String, lastName: String, age: String, dateOfBirth: String,
                            gender: String, educationLevel: String, hobbies: String)

        fun setProfilePicture(bytes: ByteArray)

        fun setBriefDescription(bio: String)
    }

    interface Presenter {
        fun getUserByName(firstName: String, lastName: String): User

        fun displayProfile(userId: Int)
    }
}