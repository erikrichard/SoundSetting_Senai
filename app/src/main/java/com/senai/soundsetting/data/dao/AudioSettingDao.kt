package com.senai.soundsetting.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senai.soundsetting.data.entity.AudioSetting

@Dao
interface AudioSettingDao {

    @Query("SELECT * FROM audiosetting")
    suspend fun getAll(): List<AudioSetting>?

    @Query("SELECT * FROM audiosetting WHERE uid = :id")
    suspend fun getById(id: Int): AudioSetting

    @Query("UPDATE audiosetting SET name = :newName WHERE uid = :id")
    suspend fun updateName(id: Int, newName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudioSetting(audioSetting: AudioSetting)

    @Delete
    suspend fun deleteAudioSetting(audioSetting: AudioSetting)

    @Query("SELECT volumeLevel FROM audiosetting WHERE uid = :id")
    suspend fun getVolumeLevel(id: Int): Int

    @Query("SELECT bass FROM audiosetting WHERE uid = :id")
    suspend fun getBassLevel(id: Int) : Int

    @Query("SELECT mid FROM audiosetting WHERE uid = :id")
    suspend fun getMidLevel(id: Int) : Int

    @Query("SELECT treble FROM audiosetting WHERE uid = :id")
    suspend fun getTrebleLevel(id: Int) : Int

}