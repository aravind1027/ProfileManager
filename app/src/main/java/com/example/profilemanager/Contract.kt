package com.example.profilemanager

interface Contract {
    interface View {
    }

    interface Presenter {
        fun addUser(user: User)

        fun onDestroy()
    }

}