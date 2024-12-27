package com.senai.soundsetting.data.persistance

import android.content.Context
import javax.inject.Inject

class PersistanceManager @Inject constructor(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("AudioSettingProfile", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    fun saveData(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }
    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }


}