package com.senai.soundsetting.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.senai.soundsetting.R
import com.senai.soundsetting.data.entity.AudioSetting
import com.senai.soundsetting.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val TAG = this::class.simpleName
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var addProfileButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.profile_list)

        //Configura o recycleView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addProfileButton = view.findViewById(R.id.add_profile)

        // Observa mudanças na lista de Profiles e atualiza o recycleView
        viewModel.profiles.observe(viewLifecycleOwner, Observer<List<AudioSetting>?> { profiles ->
            val adapter = ProfileAdapter(profiles) { profile ->
                //Callback chamado quando um novo profile é selecionado pelo usuario
                viewModel.selectProfile(profile)
            }
            recyclerView.adapter = adapter
            hideAddProfileButton(profiles?.size ?: Constants.NB_OF_EMPTY_PROFILES)
        })

        // Mostra um toast quando um novo profile for selecionado
        viewModel.selectedProfile.observe(viewLifecycleOwner, Observer<AudioSetting?> { profile ->
            if (profile != null) {
                Log.i(TAG, "Selected profile: $profile")
                Toast.makeText(context, "Selected: ${profile.name}", Toast.LENGTH_SHORT).show()
            }
        })

        //Mostra a dialog quando o botao de adicionar profile for clicado
        addProfileButton.setOnClickListener {
            Log.i(TAG, "addProfileButton clicked")
            showAddProfileDialog()
        }
    }
    //Mostra uma Dialog em tela com o objetivo de adicionar um novo profile
    private fun showAddProfileDialog() {
        Log.i(TAG, "showAddProfileDialog")
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_profile, null)
        val editTextProfileName = dialogView.findViewById<EditText>(R.id.editTextProfileName)
        val buttonAddProfile = dialogView.findViewById<Button>(R.id.buttonAddProfile)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogView)
            .create()

        buttonAddProfile.setOnClickListener {
            val profileName = editTextProfileName.text.toString().trim()
            if (profileName.isNotEmpty()) {
                Log.i(TAG, "Profile name: $profileName")
                viewModel.addProfile(AudioSetting(name = profileName))
                dialog.dismiss()
            } else {
                Log.i(TAG, "Profile name cannot be empty")
                editTextProfileName.error = "Profile name cannot be empty"
            }
        }

        dialog.show()
    }

    private fun hideAddProfileButton(numbereOfProfiles: Int) {
        Log.i(TAG, "hideAddProfileButton $numbereOfProfiles")
        if (numbereOfProfiles >= 3) {
            addProfileButton.visibility = View.GONE
        }
    }
}