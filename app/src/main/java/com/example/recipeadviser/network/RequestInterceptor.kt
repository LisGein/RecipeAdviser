package com.example.recipeadviser.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchLanguage()?.let {
            requestBuilder.addHeader("Content-Language", "$it")
        }

        return chain.proceed(requestBuilder.build())
    }
}