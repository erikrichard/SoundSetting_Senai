package com.senai.soundsetting.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import javax.inject.Inject

class AudioSettingRepositoryImpl
    @Inject constructor(private val dao: AudioSettingDao) : AudioSettingRepository {
        
    private val TAG = this::class.simpleName
    private val _state = MutableLiveData<RepositoryState>()
    private var state : LiveData<RepositoryState> = _state

    init {
        Log.i(TAG, "init")
        _state.value = RepositoryState.READY
    }

    override suspend fun getAudioSettings(): List<AudioSetting>?{
        Log.i(TAG,"getAudioSettings")
        val list = dao.getAll()
        return list
    }

    override suspend fun getAudioSettingById(id: Int): AudioSetting?{
        Log.i(TAG,"getAudioSettingById - $id")
            return dao.getById(id)
    }

     override suspend fun saveAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"saveAudioSetting - $audioSetting")
         dao.insertAudioSetting(audioSetting)
         _state.value = RepositoryState.UPDATED
     }

     override suspend fun deleteAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"deleteAudioSetting - $audioSetting")
         dao.deleteAudioSetting(audioSetting)
         _state.value = RepositoryState.UPDATED
     }

    override fun getRepositoryState(): LiveData<RepositoryState> {
        return state
    }


}