package com.senai.soundsetting.data.repository

import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import javax.inject.Inject

class AudioSettingRepository
    @Inject constructor(private val dao: AudioSettingDao){

    fun getAudioSettings(): List<AudioSetting>{
        return dao.getAll()
    }

        fun getAudioSettingById(id: Int): AudioSetting?{
            return dao.getById(id)
        }

     fun saveAudioSetting(audioSetting: AudioSetting){
         dao.insertAudioSetting(audioSetting)
     }

     fun deleteAudioSetting(audioSetting: AudioSetting){
         dao.deleteAudioSetting(audioSetting)
     }



}