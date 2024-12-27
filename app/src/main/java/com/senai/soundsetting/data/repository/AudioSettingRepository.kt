package com.senai.soundsetting.data.repository

import android.util.Log
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import javax.inject.Inject

class AudioSettingRepository
    @Inject constructor(private val dao: AudioSettingDao){
        
        private val TAG = this::class.simpleName

    suspend fun getAudioSettings(): List<AudioSetting>?{
        Log.i(TAG,"getAudioSettings")
        val list = dao.getAll()
        return list
    }

    suspend fun getAudioSettingById(id: Int): AudioSetting?{
        Log.i(TAG,"getAudioSettingById - $id")
            return dao.getById(id)
    }

     suspend fun saveAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"saveAudioSetting - $audioSetting")
         dao.insertAudioSetting(audioSetting)
     }

     suspend fun deleteAudioSetting(audioSetting: AudioSetting){
         Log.i(TAG,"deleteAudioSetting - $audioSetting")
         dao.deleteAudioSetting(audioSetting)
     }



}