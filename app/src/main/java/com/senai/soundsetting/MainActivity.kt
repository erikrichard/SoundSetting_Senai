package com.senai.soundsetting

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.senai.soundsetting.data.persistance.PersistanceManager
import com.senai.soundsetting.profile.ProfileViewModel
import com.senai.soundsetting.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private val TAG = this::class.simpleName
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var bottomBar: BottomNavigationView
    private var nbOfProfiles = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Configura o menu inferior de acordo com a grafo de navegacao criado em res/navigation/nav_graph
        bottomBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        onNumberOfProfilesChanged(nbOfProfiles)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomBar, navController)

        viewModel.profiles.observe(this) {
                profiles ->
            nbOfProfiles = profiles?.size ?: Constants.NB_OF_EMPTY_PROFILES
            onNumberOfProfilesChanged(nbOfProfiles)
        }
    }

    private fun onNumberOfProfilesChanged(newNumberOfProfiles: Int){
        Log.i(TAG, "onNumberOfProfilesChanged $newNumberOfProfiles")
        if (newNumberOfProfiles == Constants.NB_OF_EMPTY_PROFILES ){
            bottomBar.menu.findItem(R.id.profileFragment).isVisible = true
            bottomBar.menu.findItem(R.id.bmtFragment).isVisible = false
            bottomBar.menu.findItem(R.id.volumeFragment).isVisible = false
        } else {
            bottomBar.menu.findItem(R.id.profileFragment).isVisible = true
            bottomBar.menu.findItem(R.id.bmtFragment).isVisible = true
            bottomBar.menu.findItem(R.id.volumeFragment).isVisible = true
        }
    }
}