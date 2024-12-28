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
    private var currentProfileId : Int = -1

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
         val insertedId = dao.insertAudioSetting(audioSetting)
         selectProfile(insertedId.toInt())
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
        _state.value = RepositoryState.UPDATED
    }
    private fun selectProfile(profileId:Int){
        Log.i(TAG, "selectProfileId - $profileId")
        persistanceManager.saveData(Constants.PERSISTANCE_PROFILE_KEY, profileId.toString())
        currentProfileId = profileId
        _state.value = RepositoryState.UPDATED
    }

    override fun getSelectedProfileId(): Int? {
        return currentProfileId
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
        dao.updateVolumeLevel(currentProfileId,level)
        _state.value = RepositoryState.UPDATED
    }

    override suspend fun getBassLevel(): Int {
        Log.i(TAG, "getBassLevel")
        val bassLevel = dao.getBassLevel(currentProfileId)
        Log.i(TAG, "getBassLevel - $bassLevel")
        return bassLevel
    }

    override suspend fun getMidLevel(): Int {
        Log.i(TAG, "getMidLevel")
        val midLevel = dao.getMidLevel(currentProfileId)
        Log.i(TAG, "getMidLevel - $midLevel")
        return midLevel
    }

    override suspend fun getTrebleLevel(): Int {
        Log.i(TAG, "getTrebleLevel")
        val trebleLevel = dao.getTrebleLevel(currentProfileId)
        Log.i(TAG, "getTrebleLevel - $trebleLevel")
        return trebleLevel
    }

    override suspend fun setBMTLevel(bassLevel: Int, midLevel: Int, trebleLevel: Int) {
        Log.i(TAG, "setBMTLevel - $bassLevel, $midLevel, $trebleLevel")
        dao.updateBMT(currentProfileId, bassLevel, midLevel, trebleLevel)
        _state.value = RepositoryState.UPDATED
    }

}