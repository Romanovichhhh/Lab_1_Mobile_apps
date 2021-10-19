package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        //Коннект к sharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("EMAIL_KEY", null)
        val savedPassword = sharedPreferences.getString("PASSWORD_KEY", null)

        //Проверка на существующюю сессию
        if(savedEmail?.isNotEmpty() == true && savedPassword?.isNotEmpty() == true) {
            navController.navigate(R.id.action_loginFragment_to_enterFragment)
        }
    }



}

