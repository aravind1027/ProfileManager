package com.example.profilemanager

import android.app.Application
import android.util.Log
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateProfilePresenter(
    private var view: Contract.View?,
    application: Application
) : Contract.Presenter {
    private val database = UserRoomDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())


    override fun addUser(user: User) {
        repository.deleteAll()
        repository.insert(user)

        getUserByName("Aravind", "Ganeshan")
    }

    fun getUserByName(firstName: String, lastName: String) {
        var user: User? = null

        user = repository.findByName(firstName, lastName)

        Log.d("getUserByName", "${user.firstName} ${user.lastName}")
    }

    override fun onDestroy() {
        view = null
    }
}