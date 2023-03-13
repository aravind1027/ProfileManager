package com.example.profilemanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "education_level") val educationLevel: String,
    @ColumnInfo(name = "bio") val bio: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}