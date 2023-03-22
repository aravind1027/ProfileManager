package com.example.profilemanager.database

import android.media.Image
import android.os.Build.VERSION_CODES.M
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profilemanager.R
import java.io.File.separator
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "education_level") val educationLevel: String,
    @ColumnInfo(name = "hobbies") val hobbies: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "profile_picture") val profilePicture: ByteArray,
    @ColumnInfo(name = "bio") val bio: String
) : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    fun calculateAge(): String {
        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        return Period.between(
            LocalDate.parse(dateOfBirth, dateFormatter), LocalDate.now()
        ).years.toString()
    }
}