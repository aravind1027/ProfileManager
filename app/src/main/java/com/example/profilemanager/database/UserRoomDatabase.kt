package com.example.profilemanager.database

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.profilemanager.R
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executors

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
                        "user_database"
                    )
                        .addCallback(
                            object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadExecutor().execute {
                                        // INSTANCE?.userDao()?.insertAll(populateDatabase(context))
                                    }
                                }
                            }
                        )
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private fun populateDatabase(context: Context): List<User> {
            val view = context.resources.getDrawable(R.drawable.portrait_placeholder)
            val bitmap = view.toBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val profilePicture = stream.toByteArray()

            return listOf(
                User("A", "A", "01/01/2000", "M",
                    "Bachelor's Degree", "Gardening",profilePicture, "Stuff."),
                User("B", "B", "01/01/2000", "F",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("C", "C", "01/01/2000", "M",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("D", "D", "01/01/2000", "F",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("E", "E", "01/01/2000", "M",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("F", "F", "01/01/2000", "F",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("G", "G", "01/01/2000", "M",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("H", "H", "01/01/2000", "F",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("I", "I", "01/01/2000", "M",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
                User("J", "J", "01/01/2000", "F",
                    "Bachelor's Degree", "Gardening", profilePicture, "Stuff."),
            )
        }
    }
}
