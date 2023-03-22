package com.example.profilemanager.ui.profiledetails

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.profilemanager.R
import com.example.profilemanager.databinding.ProfileDetailsBinding

class ProfileDetailsScreen : AppCompatActivity(), ProfileDetailsContract.View {
    private lateinit var presenter: ProfileDetailsContract.Presenter
    private lateinit var binding: ProfileDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = ProfileDetailsPresenter(this, application)
        presenter.displayProfile(intent.getIntExtra("USER_ID", -1))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setPersonalInfo(firstName: String, lastName: String, age: String, dateOfBirth: String,
                                 gender: String, educationLevel: String, hobbies: String) {
        val personalInfo: TextView = binding.personalInfo
        personalInfo.text =
            getString(R.string.personal_info, firstName, lastName, age, dateOfBirth, gender, educationLevel, hobbies)
    }

    override fun setProfilePicture(bytes: ByteArray) {
        val profilePicture: ImageView = binding.profilePicture
        profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
    }

    override fun setBriefDescription(bio: String) {
        val briefDesc: TextView = binding.briefDescription
        briefDesc.text = getString(R.string.bio, bio)
    }

}