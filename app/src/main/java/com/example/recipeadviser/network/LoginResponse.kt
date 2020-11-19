package com.example.recipeadviser.network

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "auth_token") val authToken: String,
    val user: String
)

