package com.senai.soundsetting.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.repository.AudioSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject constructor(private val repository: AudioSettingRepository)
        : ViewModel() {


    private val _profiles = MutableLiveData<List<AudioSetting>>().apply {
        //Todo: Extrair list do banco de dados
        value = listOf(
            AudioSetting(name = "John Doe"),
            AudioSetting(name = "Jane Smith"),
            AudioSetting(name = "Alice Johnson")
        )
    }
    val profiles: LiveData<List<AudioSetting>> get() = _profiles

    private val _selectedProfile = MutableLiveData<AudioSetting?>()
    val selectedProfile: LiveData<AudioSetting?> get() = _selectedProfile

    fun selectProfile(profile: AudioSetting) {
        _selectedProfile.value = profile
        //Todo: update shared preference with the current selected profile
    }

    fun clearSelection() {
        _selectedProfile.value = null
    }

    fun addProfile(profile: AudioSetting) {
        val updatedList = _profiles.value.orEmpty().toMutableList()
        updatedList.add(profile)
        _profiles.value = updatedList

        //Todo: Inserir novo profile no banco de dados
    }
}