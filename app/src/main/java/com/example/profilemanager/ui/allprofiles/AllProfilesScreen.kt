package com.example.profilemanager.ui.allprofiles

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.databinding.AllProfilesBinding

class AllProfilesScreen : AppCompatActivity(), AllProfilesContract.View {
    private lateinit var presenter: AllProfilesContract.Presenter
    private lateinit var binding: AllProfilesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AllProfilesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = AllProfilesPresenter(this, application)

        val users = presenter.getAllUsers()
        val profileList: RecyclerView = binding.profileList
        val profileAdapter = ProfileAdapter(this, users)
        val itemTouchHelper =
            ItemTouchHelper(
                SwipeToDeleteCallback(users, profileAdapter, profileList, presenter::removeUser)
            )

        itemTouchHelper.attachToRecyclerView(profileList)
        profileList.adapter = profileAdapter
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
}