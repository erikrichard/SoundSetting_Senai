package com.senai.soundsetting.data.repository

import android.util.Log
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import javax.inject.Inject

class AudioSettingRepositoryImpl
    @Inject constructor(private val dao: AudioSettingDao) : AudioSettingRepository {
        
        private val TAG = this::class.simpleName

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
     }

     override suspend fun deleteAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"deleteAudioSetting - $audioSetting")
         dao.deleteAudioSetting(audioSetting)
     }



}