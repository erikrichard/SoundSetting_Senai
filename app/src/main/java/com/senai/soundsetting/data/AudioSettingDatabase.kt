package com.senai.soundsetting.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting

@Database(
    entities = [AudioSetting::class],
    version = 1,
    exportSchema = false)
abstract class AudioSettingDatabase : RoomDatabase() {
    abstract fun audioSettingDao(): AudioSettingDao
}