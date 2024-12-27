package com.senai.soundsetting.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.persistance.PersistanceManager
import com.senai.soundsetting.utils.Constants
import javax.inject.Inject

class AudioSettingRepositoryImpl
    @Inject constructor(
        private val dao: AudioSettingDao,
        private val persistanceManager: PersistanceManager
    ) : AudioSettingRepository {
        
    private val TAG = this::class.simpleName
    private val _state = MutableLiveData<RepositoryState>()
    private var state : LiveData<RepositoryState> = _state
    private var currentProfileId : Int = 0
    private var currentProfile : AudioSetting? = null

    init {
        Log.i(TAG, "init")
        _state.value = RepositoryState.READY
        val persistedProfileId = persistanceManager.getData(Constants.PERSISTANCE_PROFILE_KEY, "")
        if (persistedProfileId.isNotEmpty()) {
            Log.i(TAG, "Persisted profile id: $persistedProfileId")
            currentProfileId = persistedProfileId.toInt()
        }
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

    override suspend fun editAudioSetting(id: Int, newName: String) {
       dao.updateName(id, newName)
       _state.value = RepositoryState.UPDATED
    }

    override suspend fun deleteAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"deleteAudioSetting - $audioSetting")
         dao.deleteAudioSetting(audioSetting)
         _state.value = RepositoryState.UPDATED
     }

    override fun getRepositoryState(): LiveData<RepositoryState> {
        Log.i(TAG,"getRepositoryState - ${state.value}")
        return state
    }

    override fun selectProfile(profile: AudioSetting) {
        Log.i(TAG, "selectProfile - $profile")
        persistanceManager.saveData(Constants.PERSISTANCE_PROFILE_KEY, profile.uid.toString())
        currentProfileId = profile.uid
        currentProfile = profile
        _state.value = RepositoryState.UPDATED
    }

    override fun clearProfileSelection() {
        Log.i(TAG, "clearProfileSelection")
        persistanceManager.saveData(Constants.PERSISTANCE_PROFILE_KEY, "")
        currentProfileId = 0
        _state.value = RepositoryState.UPDATED
    }

    override suspend fun getVolumeLevel(): Int {
        Log.i(TAG, "getVolumeLevel")
        val volumeLevel = dao.getVolumeLevel(currentProfileId)
        Log.i(TAG, "getVolumeLevel - $volumeLevel")
        return volumeLevel
    }

    override suspend fun setVolumeLevel(level: Int) {
        Log.i(TAG, "setVolumeLevel - $level")
        currentProfile?.volumeLevel = level
        dao.insertAudioSetting(currentProfile!!)
        _state.value = RepositoryState.UPDATED
    }
}