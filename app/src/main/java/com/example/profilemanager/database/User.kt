package com.example.profilemanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Blob

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "education_level") val educationLevel: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "profile_picture") val profilePicture: ByteArray,
    @ColumnInfo(name = "bio") val bio: String
) : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}