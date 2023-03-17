package com.example.profilemanager.ui.createprofile

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.example.profilemanager.R
import com.example.profilemanager.database.User
import com.example.profilemanager.ui.allprofiles.AllProfilesScreen
import java.io.ByteArrayOutputStream

class CreateProfileScreen : AppCompatActivity(), CreateProfileContract.View {
    private lateinit var presenter: CreateProfileContract.Presenter

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            findViewById<ImageView>(R.id.profile_picture).setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_profile)

        presenter = CreateProfilePresenter(this, application)
        bindViews()
    }

    private fun bindViews() {
        createEducationLevelDropdown()
        setOnClickForSubmitButton()
        selectImage()
        setOnClickForAllProfilesButton()
    }

    private fun createEducationLevelDropdown() {
        val educationLevelDropdown: Spinner = findViewById(R.id.education_level)
        ArrayAdapter.createFromResource(
            this,
            R.array.education_level,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            educationLevelDropdown.adapter = adapter
        }
    }

    private fun setOnClickForSubmitButton() {
        val submitButton: Button = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            submitData()
            clearData()
        }
    }

    private fun setOnClickForAllProfilesButton() {
        val allProfilesButton: Button = findViewById(R.id.all_profiles_button)

        allProfilesButton.setOnClickListener {
            val allProfilesScreen = Intent(this, AllProfilesScreen::class.java)
            startActivity(allProfilesScreen)
        }
    }

    private fun submitData() {
        val firstName: String = findViewById<EditText>(R.id.first_name).text.toString()
        val lastName: String = findViewById<EditText>(R.id.last_name).text.toString()
        val age: String = findViewById<EditText>(R.id.age).text.toString()
        val dateOfBirth: String = findViewById<EditText>(R.id.date_of_birth).text.toString()
        val gender: String =
            findViewById<RadioButton>(findViewById<RadioGroup>(R.id.gender_select).checkedRadioButtonId).text.toString()
        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        val educationLevel: String = findViewById<Spinner>(R.id.education_level).selectedItem.toString()
        val bio: String = findViewById<EditText>(R.id.bio).text.toString()

        presenter.addUser(
            User(
                firstName, lastName, age, dateOfBirth, gender, educationLevel,
                imageViewToByteArray(profilePicture), bio
            )
        )
    }

    private fun imageViewToByteArray(view: ImageView): ByteArray {
        val bitmap = view.drawable.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }

    private fun clearData() {
        findViewById<EditText>(R.id.first_name).text.clear()
        findViewById<EditText>(R.id.last_name).text.clear()
        findViewById<EditText>(R.id.age).text.clear()
        findViewById<EditText>(R.id.date_of_birth).text.clear()
        findViewById<RadioGroup>(R.id.gender_select).clearCheck()
        findViewById<Spinner>(R.id.education_level).setSelection(0)
        findViewById<ImageView>(R.id.profile_picture).setImageResource(R.drawable.portrait_placeholder)
        findViewById<EditText>(R.id.bio).text.clear()
    }

    private fun selectImage() {
        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        profilePicture.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


}