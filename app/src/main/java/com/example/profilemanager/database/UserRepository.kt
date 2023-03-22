package com.example.profilemanager.database

import com.example.profilemanager.database.User
import com.example.profilemanager.database.UserDao

class UserRepository(private val userDao: UserDao) {
    fun getAll(): MutableList<User> {
        return userDao.getAll()
    }

    fun findByName(firstName: String, lastName: String): User {
        return userDao.findByName(firstName, lastName)
    }

    fun findById(userId: Int): User {
        return userDao.findById(userId)
    }

    fun insert(user: User) {
        userDao.insert(user)
    }

    fun insertAll(userList: List<User>) {
        userDao.insertAll(userList)
    }

    fun delete(user: User) {
        userDao.delete(user)
    }
}