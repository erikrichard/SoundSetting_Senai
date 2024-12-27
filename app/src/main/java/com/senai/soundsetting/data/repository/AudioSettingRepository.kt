package com.senai.soundsetting.data.repository

import android.util.Log
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import javax.inject.Inject

class AudioSettingRepository
    @Inject constructor(private val dao: AudioSettingDao){

    suspend fun getAudioSettings(): List<AudioSetting>?{
        Log.i(this::class.simpleName,"getAudioSettings")
        val list = dao.getAll()
        return list
    }

    suspend fun getAudioSettingById(id: Int): AudioSetting?{
        Log.i(this::class.simpleName,"getAudioSettingById - $id")
            return dao.getById(id)
    }

     suspend fun saveAudioSetting(audioSetting: AudioSetting){
         Log.i(this::class.simpleName,"saveAudioSetting - $audioSetting")
         dao.insertAudioSetting(audioSetting)
     }

     suspend fun deleteAudioSetting(audioSetting: AudioSetting){
         Log.i(this::class.simpleName,"deleteAudioSetting - $audioSetting")
         dao.deleteAudioSetting(audioSetting)
     }



}