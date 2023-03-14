package com.example.profilemanager.ui.allprofiles

import com.example.profilemanager.database.User

interface AllProfilesContract {
    interface View {
    }

    interface Presenter {
        fun getAllUsers(): List<User>
    }
}