package com.senai.soundsetting.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senai.soundsetting.profile.data.Profile

class ProfileViewModel : ViewModel() {
    private val _profiles = MutableLiveData<List<Profile>>().apply {
        //Todo: Extrair list do banco de dados
        value = listOf(
            Profile("John Doe"),
            Profile("Jane Smith"),
            Profile("Alice Johnson")
        )
    }
    val profiles: LiveData<List<Profile>> get() = _profiles

    private val _selectedProfile = MutableLiveData<Profile?>()
    val selectedProfile: LiveData<Profile?> get() = _selectedProfile

    fun selectProfile(profile: Profile) {
        _selectedProfile.value = profile
        //Todo: update shared preference with the current selected profile
    }

    fun clearSelection() {
        _selectedProfile.value = null
    }

    fun addProfile(profile: Profile) {
        val updatedList = _profiles.value.orEmpty().toMutableList()
        updatedList.add(profile)
        _profiles.value = updatedList

        //Todo: Inserir novo profile no banco de dados
    }
}