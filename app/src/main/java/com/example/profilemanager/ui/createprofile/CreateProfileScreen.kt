package com.example.profilemanager.ui.createprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import com.example.profilemanager.R
import com.example.profilemanager.database.User
import com.example.profilemanager.ui.allprofiles.AllProfilesScreen

class CreateProfileScreen : AppCompatActivity(), CreateProfileContract.View {
    private lateinit var presenter: CreateProfileContract.Presenter
    private var id = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_profile)

        presenter = CreateProfilePresenter(application)
        bindViews()
    }

    private fun bindViews() {
        createEducationLevelDropdown()
        setOnClickForSubmitButton()
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
        val educationLevel: String = findViewById<Spinner>(R.id.education_level).selectedItem.toString()
        val bio: String = findViewById<EditText>(R.id.bio).text.toString()

        presenter.addUser(User(firstName, lastName, age, dateOfBirth, gender, educationLevel, bio))
    }

    private fun clearData() {
        findViewById<EditText>(R.id.first_name).text.clear()
        findViewById<EditText>(R.id.last_name).text.clear()
        findViewById<EditText>(R.id.age).text.clear()
        findViewById<EditText>(R.id.date_of_birth).text.clear()
        findViewById<RadioGroup>(R.id.gender_select).clearCheck()
        findViewById<Spinner>(R.id.education_level).setSelection(0)
        findViewById<EditText>(R.id.bio).text.clear()
    }
}