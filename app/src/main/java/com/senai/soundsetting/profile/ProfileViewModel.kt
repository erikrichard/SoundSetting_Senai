package com.senai.soundsetting.profile

import android.provider.MediaStore.Audio
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.persistance.PersistanceManager
import com.senai.soundsetting.data.repository.AudioSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class ProfileViewModel
    @Inject constructor(
        private val repository: AudioSettingRepository,
        private val persistanceManager: PersistanceManager
    )
        : ViewModel() {
            
    private val TAG = this::class.simpleName
    private val _profiles = MutableLiveData<List<AudioSetting>>().apply {
        Log.i(TAG, "trying to fetch profiles")
        viewModelScope.launch {
            value = repository.getAudioSettings()
        }
    }
    val profiles: LiveData<List<AudioSetting>?> get() = _profiles

    private val _selectedProfile = MutableLiveData<AudioSetting?>()
    val selectedProfile: LiveData<AudioSetting?> get() = _selectedProfile

    fun selectProfile(profile: AudioSetting) {
        _selectedProfile.value = profile
        persistanceManager.saveData("selectedProfileId", profile.uid.toString())
    }

    fun clearSelection() {
        Log.i(TAG, "clearSelection")
        _selectedProfile.value = null
        persistanceManager.saveData("selectedProfileId", "")
    }

    fun addProfile(profile: AudioSetting) {
        Log.i(TAG, "addProfile $profile")
        viewModelScope.launch {
            repository.saveAudioSetting(profile)
        }
    }
}