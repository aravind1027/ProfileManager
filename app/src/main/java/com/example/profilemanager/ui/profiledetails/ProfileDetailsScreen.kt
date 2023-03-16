package com.example.profilemanager.ui.profiledetails

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.profilemanager.R
import com.example.profilemanager.database.User

class ProfileDetailsScreen : AppCompatActivity(), ProfileDetailsContract.View {
    private lateinit var presenter: ProfileDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_details)

        presenter = ProfileDetailsPresenter(this, application)
        presenter.displayProfile(intent.getSerializableExtra("USER") as User)
    }

    override fun setPersonalInfo(firstName: String, lastName: String, age: String, dateOfBirth: String,
                                 gender: String, educationLevel: String) {
        val personalInfo: TextView = findViewById(R.id.personal_info)
        personalInfo.text =
            getString(R.string.personal_info, firstName, lastName, age, dateOfBirth, gender, educationLevel)
    }

    override fun setProfilePicture(bytes: ByteArray) {
        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
    }

    override fun setBriefDescription(bio: String) {
        val briefDesc: TextView = findViewById(R.id.brief_description)
        briefDesc.text = getString(R.string.bio, bio)
    }

}