package com.example.recipeadviser.network

import retrofit2.http.*


interface ApiService {
    @GET("updated_recipes")
    suspend fun getUpdatedRecipes(): UpdatedRecipesInfo

    @POST("auth/login")
    @Headers( "Content-Type: application/json;charset=UTF-8")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

