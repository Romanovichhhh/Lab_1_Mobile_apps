package com.example.myapplication

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           val view = inflater.inflate(R.layout.fragment_login, container, false)

       view.loginButton.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_enterFragment)
       }

        view.goToReg.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regFragment)
        }

        return view
    }


}