package com.example.profilemanager.database

import com.example.profilemanager.database.User
import com.example.profilemanager.database.UserDao

class UserRepository(private val userDao: UserDao) {
    fun getAll(): List<User> {
        return userDao.getAll()
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