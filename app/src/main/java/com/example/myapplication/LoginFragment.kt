package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_reg.view.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.loginButton.setOnClickListener {
            if(checkSharedData(view) && dataIsValid(view)) {
                Toast.makeText(requireActivity(), "Вы успешно вошли", Toast.LENGTH_SHORT)
                findNavController().navigate(R.id.action_loginFragment_to_enterFragment)
            }
        }

        view.goToReg.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regFragment)
        }

        return view
    }


    private fun checkSharedData(view: View?): Boolean {

        var checked: Boolean = false

        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("EMAIL_KEY", null)
        val savedPassword = sharedPreferences.getString("PASSWORD_KEY", null)

        val enteredEmail: String? = view?.enterEmail?.text.toString()
        val enteredPassword: String? = view?.enterPassword?.text.toString()

        if (savedEmail == enteredEmail && savedPassword == enteredPassword) {
            checked = true
            return checked
        } else {
            Toast.makeText(requireActivity(), "Такой Email/Пароль не найден", Toast.LENGTH_SHORT).show()
            return checked
        }
    }

    private fun dataIsValid(view: View?): Boolean {
        var checked: Boolean = false

        val enteredEmail: String? = view?.enterEmail?.text.toString()
        val enteredPassword: String? = view?.enterPassword?.text.toString()

        //Проверка на то, заполнены ли все поля
        if (enteredEmail?.isNotEmpty() == true && enteredPassword?.isNotEmpty() == true) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword?.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    checked = true
                    return checked //Если все проверки пройдены возвращаем true

                    //Блок els'ов где выводятся соответствующие сообщения об ошибке
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Пароль введён некорректно",
                        Toast.LENGTH_SHORT
                    ).show()
                    return checked
                }
            } else {
                Toast.makeText(requireActivity(), "Email введён некорректно", Toast.LENGTH_SHORT)
                    .show()
                return checked
            }
        } else {
            Toast.makeText(requireActivity(), "Не все поля заполнены", Toast.LENGTH_SHORT)
                .show()
            return checked
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean { //Функция проверяющая корректность введённого мыла
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}