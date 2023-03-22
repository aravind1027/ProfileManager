package com.example.profilemanager.database

import androidx.room.*
import com.example.profilemanager.database.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): MutableList<User>

    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Query("SELECT * FROM user_table WHERE id LIKE :userId")
    fun findById(userId: Int): User

    @Insert
    fun insert(user: User)

    @Insert
    fun insertAll(userList: List<User>)

    @Delete
    fun delete(user: User)
}