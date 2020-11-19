package com.example.recipeadviser

import androidx.lifecycle.ViewModel
import com.example.recipeadviser.network.Api
import com.example.recipeadviser.network.LoginRequest
import com.example.recipeadviser.network.SessionManager
import com.example.recipeadviser.network.TokenResponseResult

class UserViewModel: ViewModel() {
    var _sessionManager : SessionManager? = null

    fun setSessionManager(sessionManager: SessionManager)
    {
        _sessionManager = sessionManager
    }

    suspend fun login(email: String, password: String): TokenResponseResult {
        var result = TokenResponseResult()

        if (_sessionManager == null) {
            result.code = -1
            result.message = "SessionManager isn't initialized"
            return result
        }

        try {
            var loginResponse = Api.retrofitService.login(LoginRequest(email, password))
            if (loginResponse?.statusCode == 200) {
                _sessionManager?.saveAuthToken(loginResponse.authToken)
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