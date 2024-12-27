package com.senai.soundsetting.data.repository

import com.senai.soundsetting.data.entity.AudioSetting

interface AudioSettingRepository {
    suspend fun getAudioSettings(): List<AudioSetting>?
    suspend fun getAudioSettingById(id: Int): AudioSetting?
    suspend fun saveAudioSetting(audioSetting: AudioSetting)
    suspend fun deleteAudioSetting(audioSetting: AudioSetting)
}