package com.senai.soundsetting.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audiosetting")
data class AudioSetting(
    @PrimaryKey(autoGenerate = true)
    val uid:Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bass")
    val bass: Int,

    @ColumnInfo(name = "mid")
    val mid: Int,

    @ColumnInfo(name = "treble")
    val treble: Int,

    @ColumnInfo(name = "volumeLevel")
    val volumeLevel : Int

)
