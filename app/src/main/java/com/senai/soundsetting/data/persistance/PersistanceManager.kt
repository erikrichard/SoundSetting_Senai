package com.senai.soundsetting.data.persistance

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PersistanceManager @Inject constructor(
    @ApplicationContext private val appContext: Context) {
    private val sharedPreferences = appContext.getSharedPreferences("AudioSettingProfile", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val TAG = this::class.simpleName

    init {
        Log.i(TAG,"PersistanceManager - init")
    }

    fun saveData(key: String, value: String) {
        Log.i(TAG,"saveData - $key - $value")
        editor.putString(key, value)
        editor.apply()
    }
    fun getData(key: String, defaultValue: String): String {
        Log.i(TAG,"getData - $key - $defaultValue")
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }


}