package com.example.profilemanager.ui.allprofiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R

class AllProfilesScreen : AppCompatActivity(), AllProfilesContract.View {
    private lateinit var presenter: AllProfilesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_profiles)

        presenter = AllProfilesPresenter(this, application)

        val users = presenter.getAllUsers()
        val profileList: RecyclerView = findViewById(R.id.profile_list)

        profileList.adapter = ProfileAdapter(this, users)
    }
}