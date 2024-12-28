package com.senai.soundsetting.volume

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.soundsetting.data.repository.AudioSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolumeViewModel
@Inject constructor(
    private val repository: AudioSettingRepository
) : ViewModel() {

    private val _volumeLevel = MutableLiveData<Int>().apply { value = 50 }
    private val TAG = this::class.simpleName
    val volumeLevel: LiveData<Int> get() = _volumeLevel

    init {
       Log.i(TAG, "init")
       fetchData()
    }

    fun setVolumeLevel(level: Int) {
        Log.i(TAG, "setVolumeLevel $level")
        _volumeLevel.value = level
    }
    fun saveData() {
        Log.i(TAG, "saveData")
        viewModelScope.launch {
            repository.setVolumeLevel(volumeLevel.value!!)
        }
    }
    fun fetchData(){
        viewModelScope.launch {
            _volumeLevel.value = repository.getVolumeLevel()
        }
    }
}