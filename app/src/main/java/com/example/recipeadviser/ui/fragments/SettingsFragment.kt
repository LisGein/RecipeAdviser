package com.example.recipeadviser.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.recipeadviser.R
import com.example.recipeadviser.network.SessionManager
import com.example.recipeadviser.ui.activities.MainActivity
import java.util.*

class SettingsFragment : Fragment() {
    lateinit var locale: Locale

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ArrayAdapter.createFromResource(this.requireContext(),
                R.array.languages_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = view.findViewById<Spinner>(R.id.language_spinner)
        spinner?.adapter = adapter

        val sessionManager = SessionManager(requireActivity().application.applicationContext)
        val lang = if (sessionManager.fetchLanguage() == "en")  0  else 1
        spinner.setSelection(lang)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){

                val selectedLang = if (position == 0)   "en" else "ru"
                val s = sessionManager.fetchLanguage()
                if (s != selectedLang) {
                    sessionManager.saveLanguage(selectedLang)
                    sessionManager.setLocale(selectedLang, resources)
                    val refresh = Intent(
                        requireContext(),
                        MainActivity::class.java
                    )
                    startActivity(refresh)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)

    }


}

