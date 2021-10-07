package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_reg.*
import kotlinx.android.synthetic.main.fragment_reg.view.*
import android.content.SharedPreferences


class RegFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_reg, container, false)

        view.registrationButton.setOnClickListener {
            //saveData()
            findNavController().navigate(R.id.action_regFragment_to_loginFragment)

        }

        view.goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_regFragment_to_loginFragment)
        }

        return view
    }

    fun saveData() {
        val userEmail = addEmail.text.toString()
        testView.text  = userEmail
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", userEmail)
        }.apply()

    }

}