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
import android.text.TextUtils
import android.util.Patterns


class RegFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view =  inflater.inflate(R.layout.fragment_reg, container, false)



        view.registrationButton.setOnClickListener {
            if (dataIsValid(view)) {
                saveData(view)
                findNavController().navigate(R.id.action_regFragment_to_loginFragment)
            }
        }

        view.goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_regFragment_to_loginFragment)
        }

        return view
    }
    //Сохранение данных  внутри приложения
    private fun saveData(view: View?) {
        val userEmail = view?.addEmail?.text.toString()
        val userName = view?.addUsername?.text.toString()
        val userPassword = view?.addPassword?.text.toString()

        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("EMAIL_KEY", userEmail)
            putString("USERNAME_KEY", userName)
            putString("PASSWORD_KEY", userPassword)
        }.apply()
        Toast.makeText(requireActivity(), "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
    }

        //Проверка на правильность ввода данных
      private fun dataIsValid (view: View?) : Boolean {
        var checked : Boolean = false

        val enteredEmail : String? = view?.addEmail?.text.toString()
        val enteredUsername : String? = view?.addUsername?.text.toString()
        val enteredPassword : String? = view?.addPassword?.text.toString()
        val repeatedPassword : String? = view?.repeatPassword?.text.toString()

        //Проверка на то, заполнены ли все поля
        if(enteredEmail?.isNotEmpty() == true && enteredUsername?.isNotEmpty() == true && enteredPassword?.isNotEmpty() == true && repeatedPassword?.isNotEmpty() == true) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword?.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    if (enteredPassword == repeatedPassword) {
                        checked = true
                        return checked //Если все проверки пройдены возвращаем true

                        //Блок els'ов где выводятся соответствующие сообщения об ошибке
                    } else {
                        Toast.makeText(requireActivity(), "Пароли не совпадают", Toast.LENGTH_SHORT)
                            .show()
                        return checked
                    }
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Пароль введён некорректно",
                        Toast.LENGTH_SHORT
                    ).show()
                    return checked
                }
            } else {
                Toast.makeText(requireActivity(), "Email введён некорректно", Toast.LENGTH_SHORT).show()
                return checked
            }
        }
        else {
            Toast.makeText(requireActivity(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            return checked
        }
    }
    // Встроеная функция проверки емейла
    private fun isValidEmail(target: CharSequence?): Boolean { //Функция проверяющая корректность введённого мыла
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}