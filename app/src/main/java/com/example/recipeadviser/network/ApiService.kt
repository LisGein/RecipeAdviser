package com.example.recipeadviser.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:3002/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("updated_recipes")
    suspend fun getUpdatedRecipes(): UpdatedRecipesInfo

    @POST("auth/login")
    @Headers( "Content-Type: application/json;charset=UTF-8")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

object Api {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}