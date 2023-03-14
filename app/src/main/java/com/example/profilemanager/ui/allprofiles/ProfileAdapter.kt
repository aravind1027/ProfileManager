package com.example.profilemanager.ui.allprofiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R
import com.example.profilemanager.database.User

class ProfileAdapter(
    private val context: Context,
    private val users: List<User>
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileInfo: TextView = view.findViewById(R.id.profile_info)
        val briefDesc: TextView = view.findViewById(R.id.brief_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.profile_card, parent, false)

        return ProfileViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val (firstName, lastName, age, dateOfBirth, gender, educationLevel, bio) = users[position]

        holder.profileInfo.text =
            context.getString(R.string.personal_info, firstName, lastName, age, dateOfBirth, gender, educationLevel)
        holder.briefDesc.text = context.getString(R.string.bio, bio)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}