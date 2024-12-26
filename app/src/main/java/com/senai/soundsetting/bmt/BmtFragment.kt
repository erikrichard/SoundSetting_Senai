package com.senai.soundsetting.bmt

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

class BmtFragment : Fragment() {

    private var bassLevel: Int = 50
    private var midLevel: Int = 50
    private var trebleLevel: Int = 50

    private val viewModel: BmtViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bmt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bassSeekBar: SeekBar = view.findViewById(R.id.bassSeekBar)
        val midSeekBar: SeekBar = view.findViewById(R.id.midSeekBar)
        val trebleSeekBar: SeekBar = view.findViewById(R.id.trebleSeekBar)
        val bassText: TextView = view.findViewById(R.id.bassText)
        val midText: TextView = view.findViewById(R.id.midText)
        val trebleText: TextView = view.findViewById(R.id.trebleText)
        val saveButton: Button = view.findViewById(R.id.saveButton)

        // Observe LiveData from ViewModel
        viewModel.bassLevel.observe(viewLifecycleOwner, Observer { level ->
            bassText.text = "Bass: $level"
            bassSeekBar.progress = level
        })

        viewModel.midLevel.observe(viewLifecycleOwner, Observer { level ->
            midText.text = "Mid: $level"
            midSeekBar.progress = level
        })

        viewModel.trebleLevel.observe(viewLifecycleOwner, Observer { level ->
            trebleText.text = "Treble: $level"
            trebleSeekBar.progress = level
        })

        // Update ViewModel when SeekBar changes
        bassSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setBassLevel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        midSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setMidLevel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        trebleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setTrebleLevel(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        saveButton.setOnClickListener {
            Toast.makeText(context, "Settings saved: Bass ${viewModel.bassLevel.value}, Mid ${viewModel.midLevel.value}, Treble ${viewModel.trebleLevel.value}", Toast.LENGTH_SHORT).show()
            viewModel.saveData()
        }
    }
}