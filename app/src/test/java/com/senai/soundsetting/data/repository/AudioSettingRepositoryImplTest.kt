package com.senai.soundsetting.data.repository

import com.senai.soundsetting.data.dao.AudioSettingDao
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.data.persistance.PersistanceManager
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AudioSettingRepositoryImplTest {


    @Mock
    private val dao = mockk<AudioSettingDao>()

    @Mock
    private val persistanceManager = mockk<PersistanceManager>()
    private val SUT : AudioSettingRepositoryImpl = AudioSettingRepositoryImpl(
        dao = dao,
        persistanceManager = persistanceManager
    )
    private val validList  = listOf(
        AudioSetting(
            uid = 1,
            bass = 10,
            mid = 20,
            treble = 30,
            name = "Test"
        )
    )

    @Before
    fun setUp() {
        runTest {
            Mockito.`when`(
                dao.getAll()
            ).thenReturn(
                validList
            )
        }
    }

    @Test
    fun getAudioSettings() {
        //Arrange
        var returnedList : List<AudioSetting>?
        //Act
        runTest {
            returnedList = SUT.getAudioSettings()
            assertEquals(returnedList, validList)
        }
        //Assert
    }

    @Test
    fun getAudioSettingById() {
    }

    @Test
    fun saveAudioSetting() {
    }

    @Test
    fun deleteAudioSetting() {
    }

    @Test
    fun getRepositoryState() {
    }

    @Test
    fun selectProfile() {
    }

    @Test
    fun clearProfileSelection() {
    }
}