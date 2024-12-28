package com.senai.soundsetting.data.repository

import androidx.lifecycle.LiveData
import com.senai.soundsetting.data.entity.AudioSetting

interface AudioSettingRepository {
    suspend fun getAudioSettings(): List<AudioSetting>?
    suspend fun getAudioSettingById(id: Int): AudioSetting?
    suspend fun saveAudioSetting(audioSetting: AudioSetting)
    suspend fun editAudioSetting(id: Int, newName:String)
    suspend fun deleteAudioSetting(audioSetting: AudioSetting)
    fun getRepositoryState() : LiveData<RepositoryState>
    fun selectProfile(profile: AudioSetting)
    fun clearProfileSelection()
    fun getSelectedProfileId():Int?
    suspend fun getVolumeLevel() : Int
    suspend fun setVolumeLevel(level: Int)
    suspend fun getBassLevel() : Int
    suspend fun getMidLevel() : Int
    suspend fun getTrebleLevel() : Int
    suspend fun setBMTLevel(bassLevel: Int, midLevel : Int, trebleLevel : Int)
}