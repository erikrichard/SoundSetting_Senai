package com.senai.soundsetting.volume

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VolumeViewModel : ViewModel() {
    private val _volumeLevel = MutableLiveData<Int>().apply { value = 50 }
    val volumeLevel: LiveData<Int> get() = _volumeLevel

    fun setVolumeLevel(level: Int) {
        _volumeLevel.value = level

    }
    fun saveData() {
        //Todo: Atualizar banco de dados com o valor de volume
    }
}