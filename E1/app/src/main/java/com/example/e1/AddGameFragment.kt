package com.example.e1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e1.databinding.FragmentAddGameBinding
import com.google.android.material.snackbar.Snackbar

class AddGameFragment : Fragment() {
    private var _binding: FragmentAddGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seekBarQuantity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.txtQuantity.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Se llama cuando se toca la SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Se llama cuando se deja de tocar la SeekBar
            }
        })
        val consolas = listOf("PlayStation", "Xbox", "Nintendo Switch")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, consolas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerConsole.adapter = adapter

        binding.btnSave.setOnClickListener {
            val game = Game(
                id = binding.editId.text.toString().toInt(),
                name = binding.editName.text.toString(),
                price = binding.editPrice.text.toString().toDouble(),
                quantity = binding.txtQuantity.text.toString().toInt(),
                console = binding.spinnerConsole.selectedItem.toString(),
                pc = binding.switchPC.isChecked
            )
            Singleton.list.add(game)
            Snackbar.make(it, "Game save", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_AddGameFragment_to_ListGamesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}