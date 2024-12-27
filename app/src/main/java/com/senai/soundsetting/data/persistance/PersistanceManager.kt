package com.senai.soundsetting.data.persistance

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PersistanceManager @Inject constructor(
    @ApplicationContext private val appContext: Context) {
    private val sharedPreferences = appContext.getSharedPreferences("AudioSettingProfile", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    init {
        Log.i(this::class.simpleName,"PersistanceManager - init")
    }

    fun saveData(key: String, value: String) {
        Log.i(this::class.simpleName,"saveData - $key - $value")
        editor.putString(key, value)
        editor.apply()
    }
    fun getData(key: String, defaultValue: String): String {
        Log.i(this::class.simpleName,"getData - $key - $defaultValue")
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }


}