package com.example.profilemanager.ui.allprofiles

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.profilemanager.database.User
import com.google.android.material.snackbar.Snackbar

class SwipeToDeleteCallback(
    private var users: MutableList<User>,
    private val profileAdapter: ProfileAdapter,
    private val profileList: RecyclerView,
    private val removeUser: (User) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val profileCardToDelete: User = users[viewHolder.adapterPosition]
        val position: Int = viewHolder.adapterPosition

        users.removeAt(viewHolder.adapterPosition)
        profileAdapter.notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(profileList, "Deleted Profile", Snackbar.LENGTH_LONG)
            .addCallback(
                object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        removeUser(profileCardToDelete)
                    }
                }
            )
            .setAction(
                "Undo"
            ) {
                users.add(position, profileCardToDelete)
                profileAdapter.notifyItemInserted(position)
            }.show()
    }
}