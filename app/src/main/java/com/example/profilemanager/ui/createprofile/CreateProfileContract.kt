package com.example.profilemanager.ui.createprofile

import com.example.profilemanager.database.User

interface CreateProfileContract {
    interface View {
        fun setOnClickForSubmitButton()
    }

    interface Presenter {
        fun addUser(user: User)
    }
}