package com.senai.soundsetting.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audiosetting")
data class AudioSetting(
    @PrimaryKey(autoGenerate = true)
    val uid:Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "bass")
    var bass: Int = 0,

    @ColumnInfo(name = "mid")
    var mid: Int = 0,

    @ColumnInfo(name = "treble")
    var treble: Int = 0,

    @ColumnInfo(name = "volumeLevel")
    var volumeLevel : Int = 0,
)
