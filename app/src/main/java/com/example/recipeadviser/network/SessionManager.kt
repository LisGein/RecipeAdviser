package com.example.recipeadviser.network

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.recipeadviser.R
import java.util.*


class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val LANG = "language"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun saveLanguage(i_language: String)
    {
        val editor = prefs.edit()
        editor.putString(LANG, i_language)
        editor.apply()
    }

    fun fetchLanguage(): String? {
        return prefs.getString(LANG, "en")
    }

    fun setLocale(localeName: String, res: Resources) {
        var locale = Locale(localeName)
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, res.displayMetrics)
    }
}