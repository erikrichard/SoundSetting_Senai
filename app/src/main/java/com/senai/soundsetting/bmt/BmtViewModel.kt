package com.senai.soundsetting.bmt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.soundsetting.data.repository.AudioSettingRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BmtViewModel
@Inject constructor(
    private val repository: AudioSettingRepository
) : ViewModel() {

    private val TAG = this::class.simpleName

    private val _bassLevel = MutableLiveData<Int>().apply { value = 50 }
    val bassLevel: LiveData<Int> get() = _bassLevel

    private val _midLevel = MutableLiveData<Int>().apply { value = 50 }
    val midLevel: LiveData<Int> get() = _midLevel

    private val _trebleLevel = MutableLiveData<Int>().apply { value = 50 }
    val trebleLevel: LiveData<Int> get() = _trebleLevel

    init {
        Log.i(TAG, "init")
        viewModelScope.launch {
            _bassLevel.value = repository.getBassLevel()
            _midLevel.value = repository.getMidLevel()
            _trebleLevel.value = repository.getTrebleLevel()
        }
    }

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
        Log.i(TAG, "saveData")
        viewModelScope.launch {
            repository.setBMTLevel(
            _bassLevel.value!!,
                _midLevel.value!!,
                _trebleLevel.value!!
            )
        }
    }
}