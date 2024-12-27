package com.senai.soundsetting.di

import com.senai.soundsetting.data.repository.AudioSettingRepository
import com.senai.soundsetting.data.repository.AudioSettingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAudioSettingRepository(audioSettingRepositoryImpl: AudioSettingRepositoryImpl): AudioSettingRepository

}