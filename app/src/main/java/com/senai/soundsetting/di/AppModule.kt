package com.senai.soundsetting.di

import android.content.Context
import androidx.room.Room
import com.senai.soundsetting.data.AudioSettingDatabase
import com.senai.soundsetting.data.dao.AudioSettingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        appContext: Context
        ): AudioSettingDatabase {
        return Room.databaseBuilder(
            appContext,
            AudioSettingDatabase::class.java,
            "audioSettings.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAudioSettingDao(database: AudioSettingDatabase) : AudioSettingDao {
        return database.audioSettingDao()
    }


}