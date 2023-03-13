package com.example.profilemanager

interface CreateProfileContract {
    interface View {
    }

    interface Presenter {
        fun addUser(user: User)

        fun onDestroy()
    }
}