package com.senai.soundsetting.bmt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmtViewModel : ViewModel() {
    private val _bassLevel = MutableLiveData<Int>().apply { value = 50 }
    val bassLevel: LiveData<Int> get() = _bassLevel

    private val _midLevel = MutableLiveData<Int>().apply { value = 50 }
    val midLevel: LiveData<Int> get() = _midLevel

    private val _trebleLevel = MutableLiveData<Int>().apply { value = 50 }
    val trebleLevel: LiveData<Int> get() = _trebleLevel

    fun setBassLevel(level: Int) {
        _bassLevel.value = level
    }

    fun setMidLevel(level: Int) {
        _midLevel.value = level
    }

    fun setTrebleLevel(level: Int) {
        _trebleLevel.value = level
    }

    fun saveData(){
        //Update BD with bass, mid, treble values
    }
}