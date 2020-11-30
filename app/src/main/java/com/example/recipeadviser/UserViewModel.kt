package com.example.recipeadviser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeadviser.network.ApiClient
import com.example.recipeadviser.network.LoginRequest
import com.example.recipeadviser.network.SessionManager
import com.example.recipeadviser.network.TokenResponseResult


class ViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(mApplication) as T
    }
}

class UserViewModel(application: Application): AndroidViewModel(application) {
    var sessionManager = SessionManager(application.applicationContext)

    suspend fun login(email: String, password: String): TokenResponseResult {
        val result = TokenResponseResult()

        try {
            val loginResponse = ApiClient.getApiService(getApplication<Application>().applicationContext.applicationContext).login(LoginRequest(email, password))
            if (loginResponse.statusCode == 200) {
                sessionManager.saveAuthToken(loginResponse.authToken)
                result.code = 200
            }
        } catch (e: retrofit2.HttpException)
        {
            result.code = e.code()
            result.message = e.message()
        }

        return result
    }
}