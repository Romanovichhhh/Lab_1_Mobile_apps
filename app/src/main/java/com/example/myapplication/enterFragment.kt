package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_enter.view.*


class enterFragment : Fragment() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.exit_menu, menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            R.id.changeAccount -> {
                val preferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.clear()
                editor.apply()
                findNavController().navigate(R.id.action_enterFragment_to_loginFragment)
                return true
            }
            R.id.exitButton -> {
                activity?.finish();
                System.exit(0);

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_enter, container, false)

        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("USERNAME_KEY", null)
        if (savedUsername?.isNotEmpty() == true) {
            view.welcomeText.text = "Добро пожаловать, $savedUsername"
        }




        return view
    }


}