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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var bottomBar: BottomNavigationView
    private var nbOfProfiles = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(this::class.simpleName, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Configura o menu inferior de acordo com a grafo de navegacao criado em res/navigation/nav_graph
        bottomBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        onNumberOfProfilesChanged(nbOfProfiles)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomBar, navController)

        viewModel.profiles.observe(this) {
                profiles ->
            nbOfProfiles = profiles?.size ?: 0
            onNumberOfProfilesChanged(nbOfProfiles)
        }
    }

    private fun onNumberOfProfilesChanged(newNumberOfProfiles: Int){
        Log.i(this::class.simpleName, "onNumberOfProfilesChanged $newNumberOfProfiles")
        if (newNumberOfProfiles == 0 ){
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