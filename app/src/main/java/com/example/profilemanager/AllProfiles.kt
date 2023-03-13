package com.example.profilemanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AllProfiles : AppCompatActivity(), AllProfilesContract.View {
    private lateinit var presenter: AllProfilesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_profiles)

        presenter = AllProfilesPresenter(this, application)

        val users = presenter.getAllUsers()
        val profileList: RecyclerView = findViewById(R.id.profile_list)

        profileList.adapter = ProfileAdapter(this, users)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}