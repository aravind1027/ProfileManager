package com.example.profilemanager

interface AllProfilesContract {
    interface View {

    }

    interface Presenter {
        fun getAllUsers(): List<User>

        fun onDestroy()
    }
}