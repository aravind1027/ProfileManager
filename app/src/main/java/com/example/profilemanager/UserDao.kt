package com.example.profilemanager

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}