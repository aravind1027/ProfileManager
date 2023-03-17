package com.example.profilemanager.ui.allprofiles

import com.example.profilemanager.database.User

interface AllProfilesContract {
    interface View {
    }

    interface Presenter {
        fun getAllUsers(): MutableList<User>

        fun addUser(user: User)

        fun removeUser(user: User)
    }
}