package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_reg.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContentView(R.layout.fragment_reg)






        loadData()

        val regButton : Button? = findViewById(R.id.registrationButton)
        regButton?.setOnClickListener {
            //val userPassword : String? =
            if (addPassword.text.toString() == repeatPassword.text.toString()) {
                saveData()
                setContentView(R.layout.fragment_login)
            }
            else{
                Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show()
            }

            val loginButton : Button? = findViewById(R.id.loginButton)
            loginButton?.setOnClickListener {
                val enteredEmail : String? = enterEmail.text.toString()
                val enteredPassword : String? = enterPassword.text.toString()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val savedEmail = sharedPreferences.getString("EMAIL_KEY", null)
                val savedPassword = sharedPreferences.getString("PASSWORD_KEY", null)

                if (enteredEmail == savedEmail && enteredPassword == savedPassword) {
                    setContentView(R.layout.fragment_enter)
                }
                else {
                    Toast.makeText(this, "You entered wrong data", Toast.LENGTH_SHORT).show()
                }
            }
        }





    }

    fun saveData() {
        val userEmail = addEmail.text.toString()
        val userName = addUsername.text.toString()
        val userPassword = addPassword.text.toString()
        testView.text  = userEmail
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("EMAIL_KEY", userEmail)
            putString("USERNAME_KEY", userName)
            putString("PASSWORD_KEY", userPassword)
        }.apply()
        editor.commit()

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
    }


     fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("EMAIL_KEY", null)
        val savedUsername = sharedPreferences.getString("USERNAME_KEY", null)
        val savedPassword = sharedPreferences.getString("PASSWORD_KEY", null)

        var testView : TextView? = findViewById(R.id.testView)
        testView?.text  = savedEmail
    }



}

