package com.senai.soundsetting.profile

import android.provider.MediaStore.Audio
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.repository.AudioSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class ProfileViewModel
    @Inject constructor(private val repository: AudioSettingRepository)
        : ViewModel() {


    private val _profiles = MutableLiveData<List<AudioSetting>>().apply {
        Log.i(this::class.simpleName, "trying to fetch profiles")
        viewModelScope.launch {
            value = repository.getAudioSettings()
        }
    }
    val profiles: LiveData<List<AudioSetting>?> get() = _profiles

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
        viewModelScope.launch {
            repository.saveAudioSetting(profile)
        }
    }
}