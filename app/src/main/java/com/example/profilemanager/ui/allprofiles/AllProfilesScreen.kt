package com.example.profilemanager.ui.allprofiles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R
import com.example.profilemanager.database.User
import com.example.profilemanager.ui.profiledetails.ProfileDetailsScreen
import com.google.android.material.snackbar.Snackbar

class AllProfilesScreen : AppCompatActivity(), AllProfilesContract.View {
    private lateinit var presenter: AllProfilesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_profiles)

        presenter = AllProfilesPresenter(this, application)

        val users = presenter.getAllUsers()
        val profileList: RecyclerView = findViewById(R.id.profile_list)
        val profileAdapter = ProfileAdapter(this, users)

        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(users, profileAdapter, profileList))

        itemTouchHelper.attachToRecyclerView(profileList)
        profileList.adapter = profileAdapter
    }
}