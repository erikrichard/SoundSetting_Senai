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

class ProfileAdapter(private var profiles: List<AudioSetting>?,
                     private val onProfileSelected: (AudioSetting) -> Unit) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION
    private var onEditProfileListener: ((position:AudioSetting) -> Unit)? = null
    private var onDeleteProfileListener: ((position:AudioSetting) -> Unit)? = null
    private val TAG = this::class.simpleName

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileButton: ImageButton = itemView.findViewById(R.id.profileButton)
        val profileName: TextView = itemView.findViewById(R.id.profileName)
        val editProfileButton: ImageButton = itemView.findViewById(R.id.editProfileButton)
        val deleteProfileButton: ImageButton = itemView.findViewById(R.id.deleteProfileButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Log.i(TAG, "onBindViewHolder")
        val profile = profiles?.get(position)
        holder.profileName.text = profile?.name

        holder.itemView.isSelected = (selectedPosition == position)
        Log.i(TAG, "onBindViewHolder position = $position, selectedPosition = $selectedPosition  isSelected = ${selectedPosition == position}")
        holder.itemView.setOnClickListener {
            if (selectedPosition != position) {
                val previousPosition = selectedPosition
                selectedPosition = position
                Log.i(TAG, "SelectedPosition $selectedPosition")
                notifyItemChanged(previousPosition)  // Deselect previous
                notifyItemChanged(selectedPosition)  // Select new
                if (profile != null) {
                    Log.i(TAG, "Profile selected: $profile")
                    onProfileSelected(profile)
                }
            }
        }

        // Delegate the button click to the item view click
        holder.profileButton.setOnClickListener {
            Log.i(TAG, "Profile button clicked")
            holder.itemView.performClick()
        }

        holder.editProfileButton.setOnClickListener{
            if (profile != null)
                onEditProfileListener?.invoke(profile)
        }

        holder.deleteProfileButton.setOnClickListener{
            if (profile != null)
                onDeleteProfileListener?.invoke(profile)
        }

    }

    override fun getItemCount(): Int {
        val size = profiles?.size ?: 0
        return size
    }
    fun updateProfiles(profiles: List<AudioSetting>?){
        this.profiles = profiles
        notifyDataSetChanged()
    }
    fun setOnEditProfileListener(onEditProfileListener: ((position:AudioSetting) -> Unit)){
        this.onEditProfileListener = onEditProfileListener
    }
    fun setOnDeleteProfileListener(onDeleteProfileListener: ((position:AudioSetting) -> Unit)){
        this.onDeleteProfileListener = onDeleteProfileListener
    }

    fun selectProfile(profileId:Int?){
        val position = profiles?.indexOfFirst { profile -> profile.uid == profileId }
        Log.i(TAG, "selectProfile position  = $position , size = ${profiles?.size}")
        if(position != null && position != -1){
            val previousPosition = selectedPosition
            selectedPosition = position

            notifyItemChanged(previousPosition)  // Deselect previous
            notifyItemChanged(selectedPosition)  // Select new

        }
    }
}