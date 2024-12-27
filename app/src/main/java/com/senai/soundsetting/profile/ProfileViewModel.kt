package com.senai.soundsetting.profile

import android.provider.MediaStore.Audio
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.persistance.PersistanceManager
import com.senai.soundsetting.data.repository.AudioSettingRepository
import com.senai.soundsetting.data.repository.RepositoryState
import com.senai.soundsetting.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject constructor(
        private val repository: AudioSettingRepository
    )
        : ViewModel() {

    private val TAG = this::class.simpleName
    private val _profiles = MutableLiveData<List<AudioSetting>>().apply {
        value = emptyList()
    }
    val profiles: LiveData<List<AudioSetting>?> get() = _profiles

    private val _selectedProfile = MutableLiveData<AudioSetting?>()
    val selectedProfile: LiveData<AudioSetting?> get() = _selectedProfile

    init {
        Log.i(TAG, "init")
        repository.getRepositoryState().observeForever {
            Log.i(TAG, "Repository state changed: $it")
            fetchProfiles()
        }
    }

    fun selectProfile(profile: AudioSetting) {
        Log.i(TAG, "selectProfile $profile")
        _selectedProfile.value = profile
        repository.selectProfile(profile)
    }

    fun clearSelection() {
        Log.i(TAG, "clearSelection")
        _selectedProfile.value = null
        repository.clearProfileSelection()
    }

    fun addProfile(profile: AudioSetting) {
        Log.i(TAG, "addProfile $profile")
        viewModelScope.launch {
            repository.saveAudioSetting(profile)
        }
    }

    fun deleteProfile(profile: AudioSetting) {
        Log.i(TAG, "deleteProfile $profile")
        viewModelScope.launch {
            repository.deleteAudioSetting(profile)
        }
    }

    private fun fetchProfiles() {
        Log.i(TAG, "fetchProfiles")
        viewModelScope.launch {
            _profiles.value = repository.getAudioSettings()
        }
    }
}