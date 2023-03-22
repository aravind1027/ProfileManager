package com.example.profilemanager.ui.createprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.R


class HobbyAdapter(
    private val context: Context,
    private val hobbies: List<String>,
    private val onItemCheckListener: OnItemCheckListener
) : RecyclerView.Adapter<HobbyAdapter.HobbyViewHolder>() {

    interface OnItemCheckListener {
        fun onItemCheck(hobby: String)
        fun onItemUncheck(hobby: String)
    }

    class HobbyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkbox)
        val hobbyName: TextView = view.findViewById(R.id.hobby_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.hobby_item, parent, false)

        return HobbyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        val hobby = hobbies[position]

        holder.hobbyName.text = hobby
        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.isChecked = isChecked

            if (isChecked) {
                onItemCheckListener.onItemCheck(hobby)
            } else {
                onItemCheckListener.onItemUncheck(hobby)
            }
        }
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }
}