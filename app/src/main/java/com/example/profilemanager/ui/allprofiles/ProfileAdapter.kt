package com.example.profilemanager.ui.allprofiles

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R
import com.example.profilemanager.database.User
import com.example.profilemanager.ui.profiledetails.ProfileDetailsScreen

class ProfileAdapter(
    private val context: Context,
    private val users: List<User>
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileCard: CardView = view.findViewById(R.id.card_layout)
        val profilePicture: ImageView = view.findViewById(R.id.profile_picture)
        val profileInfo: TextView = view.findViewById(R.id.profile_info)
        val briefDesc: TextView = view.findViewById(R.id.brief_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.profile_card, parent, false)

        return ProfileViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val user = users[position]
        val (firstName, lastName, age, dateOfBirth, gender, educationLevel, profilePicture, bio) = user
        val profileDetailsScreen = Intent(context, ProfileDetailsScreen::class.java)
        profileDetailsScreen.putExtra("USER", user)

        holder.profileCard.setOnClickListener {
            startActivity(context, profileDetailsScreen, null)
        }
        holder.profileInfo.text =
            context.getString(R.string.personal_info, firstName, lastName, age, dateOfBirth, gender, educationLevel)
        holder.profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(profilePicture, 0, profilePicture.size))
        holder.briefDesc.text = context.getString(R.string.bio, bio)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}