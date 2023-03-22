package com.example.profilemanager.ui.createprofile

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R
import com.example.profilemanager.database.User
import com.example.profilemanager.databinding.CreateProfileBinding
import com.example.profilemanager.ui.allprofiles.AllProfilesScreen
import java.io.ByteArrayOutputStream
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CreateProfileScreen : AppCompatActivity(), CreateProfileContract.View {
    private lateinit var presenter: CreateProfileContract.Presenter
    private lateinit var binding: CreateProfileBinding
    private var selectedHobbies: MutableList<String> = mutableListOf()

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            binding.profilePicture.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = CreateProfilePresenter(this, application)
        bindViews()
    }

    override fun setOnClickForSubmitButton() {
        val submitButton: Button = binding.submitButton

        submitButton.setOnClickListener {
            if (validateInputs()) {
                submitData()
                clearData()
            }
        }
    }

    /* Private Functions */

    private fun bindViews() {
        createEducationLevelDropdown()
        createHobbiesCheckboxList()
        selectImage()
        setOnClickForAllProfilesButton()
    }

    private fun createEducationLevelDropdown() {
        val educationLevelDropdown: Spinner = binding.educationLevel
        ArrayAdapter.createFromResource(
            this,
            R.array.education_level,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            educationLevelDropdown.adapter = adapter
        }
    }

    private fun createHobbiesCheckboxList() {
        val hobbies: RecyclerView = binding.hobbies
        hobbies.adapter =
            HobbyAdapter(
                this,
                resources.getStringArray(R.array.hobbies).toList(),
                object : HobbyAdapter.OnItemCheckListener {
                    override fun onItemCheck(hobby: String) {
                        selectedHobbies.add(hobby)
                    }

                    override fun onItemUncheck(hobby: String) {
                        selectedHobbies.remove(hobby)
                    }
                })
        hobbies.layoutManager = GridLayoutManager(this, 2)
    }

    private fun unCheckHobbies() {
        /* TODO:
         *     This is a rather inefficient and brute force way of clearing the checkboxes.
         *     Try to find a more elegant way.
         */

        val hobbies: RecyclerView = binding.hobbies
        hobbies.adapter =
            HobbyAdapter(
                this,
                resources.getStringArray(R.array.hobbies).toList(),
                object : HobbyAdapter.OnItemCheckListener {
                    override fun onItemCheck(hobby: String) {
                        selectedHobbies.add(hobby)
                    }

                    override fun onItemUncheck(hobby: String) {
                        selectedHobbies.remove(hobby)
                    }
                })
        hobbies.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setOnClickForAllProfilesButton() {
        val allProfilesButton: Button = binding.allProfilesButton

        allProfilesButton.setOnClickListener {
            val allProfilesScreen = Intent(this, AllProfilesScreen::class.java)
            startActivity(allProfilesScreen)
        }
    }

    private fun submitData() {
        presenter.addUser(
            User(
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                binding.dateOfBirth.text.toString(),
                findViewById<RadioButton>(binding.genderSelect.checkedRadioButtonId).text.toString(),
                binding.educationLevel.selectedItem.toString(),
                selectedHobbies.joinToString(separator = ", ").trim(),
                imageViewToByteArray(binding.profilePicture),
                binding.bio.text.toString()
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
        binding.firstName.text.clear()
        binding.lastName.text.clear()
        binding.dateOfBirth.text.clear()
        binding.genderSelect.clearCheck()
        binding.educationLevel.setSelection(0)
        unCheckHobbies()
        binding.profilePicture.setImageResource(R.drawable.portrait_placeholder)
        binding.bio.text.clear()
    }

    private fun selectImage() {
        val profilePicture: ImageView = binding.profilePicture
        profilePicture.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun validateInputs(): Boolean {
        val firstName: String = binding.firstName.text.toString()
        val lastName: String = binding.lastName.text.toString()
        val gender: String = findViewById<RadioButton>(binding.genderSelect.checkedRadioButtonId)?.text.toString()
        val bio: String = binding.bio.text.toString()
        val nameRegex = Regex("^[A-Za-z]+$")

        if (
            firstName.matches(nameRegex) &&
            lastName.matches(nameRegex) &&
            validateDateOfBirth() &&
            gender.isNotEmpty() &&
            bio.isNotEmpty()
        ) {
            return true
        }

        val errorMsg =
            "Make sure all fields have been filled and use letters only in name fields."
        Toast.makeText(applicationContext, errorMsg, Toast.LENGTH_LONG).show()

        return false
    }

    private fun validateDateOfBirth(): Boolean {
        val dateOfBirth: String = findViewById<EditText>(R.id.date_of_birth).text.toString()
        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        try {
            dateFormatter.parse(dateOfBirth)
        } catch (e: DateTimeParseException) {
            return false
        }

        return true
    }
}