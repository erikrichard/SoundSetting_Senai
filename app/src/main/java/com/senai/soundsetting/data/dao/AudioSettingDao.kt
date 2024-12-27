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
    fun getAll(): List<AudioSetting>

    @Query("SELECT * FROM audiosetting WHERE uid = :id")
    fun getById(id: Int): AudioSetting

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudioSetting(audioSetting: AudioSetting)

    @Delete
    fun deleteAudioSetting(audioSetting: AudioSetting)
}