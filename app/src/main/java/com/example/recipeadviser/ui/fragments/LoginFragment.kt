package com.example.recipeadviser.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeadviser.R
import com.example.recipeadviser.UserViewModel
import kotlinx.coroutines.*

class LoginFragment: Fragment()
{
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val emailEditText = view.findViewById<TextView>(R.id.email)
        val passwordEditText = view.findViewById<TextView>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isEmpty() || password.isEmpty())
                showErrorMessage(view.context, "Input email and password")
            else
                login(email, password, view.context)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun login(email: String, password: String, context: Context) {
        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            val result = userViewModel.login(email, password)
            if (result.code == 200) {
                findNavController().popBackStack()
            } else {
                showErrorMessage(context, result.message.toString())
            }
        }
    }

    private fun showErrorMessage(context: Context, errorMessage: String)
    {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}