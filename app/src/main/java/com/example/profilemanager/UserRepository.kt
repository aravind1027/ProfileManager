package com.example.profilemanager

import androidx.annotation.WorkerThread

class UserRepository(private val userDao: UserDao) {
    fun getAll() {
        userDao.getAll()
    }

    fun findByName(firstName: String, lastName: String): User {
        return userDao.findByName(firstName, lastName)
    }

    fun insert(user: User) {
        userDao.insert(user)
    }

    fun deleteAll() {
        userDao.deleteAll()
    }
}