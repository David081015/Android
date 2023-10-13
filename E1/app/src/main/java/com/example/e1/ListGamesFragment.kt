package com.example.e1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e1.databinding.FragmentListGamesBinding

class ListGamesFragment : Fragment() {
    private var _binding: FragmentListGamesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sw = binding.switchMode
        sw.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sw.text = "Light mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sw.text = "Dark mode"
            }
        }
        //MENU
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_list_games,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.menu_ListGamesFragment_to_AddGameFragment ->{
                        findNavController().navigate(R.id.action_ListGamesFragment_to_AddGameFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)

        binding.recyclerview.adapter = ListGamesAdapter({
            DeleteGame(it)
        })
    }

    fun DeleteGame(game: Game){
        val dialog = DeleteGameDialogFragment(game, {
            binding.recyclerview.adapter = ListGamesAdapter({
                DeleteGame(it)
            })
        })
        activity?.let { dialog.show(it.supportFragmentManager, "DeleteGameDialogFragment") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}