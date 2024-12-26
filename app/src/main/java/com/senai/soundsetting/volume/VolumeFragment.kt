package com.senai.soundsetting.volume

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.senai.soundsetting.R

class VolumeFragment : Fragment() {

    private val viewModel: VolumeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_volume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val volumeSeekBar: SeekBar = view.findViewById(R.id.volumeSeekBar)
        val currentVolumeText: TextView = view.findViewById(R.id.currentVolumeText)
        val saveButton: Button = view.findViewById(R.id.saveButton)

        // Observe LiveData from ViewModel
        viewModel.volumeLevel.observe(viewLifecycleOwner, Observer { level ->
            currentVolumeText.text = "Current Volume: $level"
            volumeSeekBar.progress = level
        })

        // Update ViewModel when SeekBar changes
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setVolumeLevel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        saveButton.setOnClickListener {
            Toast.makeText(context, "Volume set to ${viewModel.volumeLevel.value}", Toast.LENGTH_SHORT).show()
        }
    }
}