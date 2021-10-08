package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_enter.view.*


class enterFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_enter, container, false)

        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("USERNAME_KEY", null)
        if (savedUsername?.isNotEmpty() == true) {
            view.welcomeText.text = "Добро пожаловать, $savedUsername"
        }


        view.exitButton.setOnClickListener {
//            val preferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//            val editor = preferences.edit()
//            editor.clear()
//            editor.apply()
            findNavController().navigate(R.id.action_enterFragment_to_loginFragment)
        }

        return view
    }


}