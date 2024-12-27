package com.senai.soundsetting.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audiosetting")
data class AudioSetting(
    @PrimaryKey(autoGenerate = true)
    val uid:Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bass")
    val bass: Int = 0,

    @ColumnInfo(name = "mid")
    val mid: Int = 0,

    @ColumnInfo(name = "treble")
    val treble: Int = 0,

    @ColumnInfo(name = "volumeLevel")
    val volumeLevel : Int = 0,
)
