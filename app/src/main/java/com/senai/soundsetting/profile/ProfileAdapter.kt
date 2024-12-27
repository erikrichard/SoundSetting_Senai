package com.senai.soundsetting.profile

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senai.soundsetting.R
import com.senai.soundsetting.data.entity.AudioSetting
import kotlin.math.log

class ProfileAdapter(private val profiles: List<AudioSetting>?,
                     private val onProfileSelected: (AudioSetting) -> Unit) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileButton: ImageButton = itemView.findViewById(R.id.profileButton)
        val profileName: TextView = itemView.findViewById(R.id.profileName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val profile = profiles?.get(position)
        holder.profileName.text = profile?.name

        holder.itemView.isSelected = (selectedPosition == position)

        holder.itemView.setOnClickListener {
            if (selectedPosition != position) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)  // Deselect previous
                notifyItemChanged(selectedPosition)  // Select new
                if (profile != null) {
                    onProfileSelected(profile)
                }
            }
        }

        // Delegate the button click to the item view click
        holder.profileButton.setOnClickListener {
            holder.itemView.performClick()
        }
    }

    override fun getItemCount(): Int {

        return profiles?.size ?: 0
    }
}