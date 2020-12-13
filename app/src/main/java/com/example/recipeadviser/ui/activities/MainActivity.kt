package com.example.recipeadviser.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeadviser.R
import com.example.recipeadviser.network.SessionManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sessionManager = SessionManager(application.applicationContext)
        sessionManager.fetchLanguage()?.let { sessionManager.setLocale(it, resources) }

        setContentView(R.layout.activity_main)

    }
}
