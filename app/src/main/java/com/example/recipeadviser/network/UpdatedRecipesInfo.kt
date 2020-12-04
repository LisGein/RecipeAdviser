package com.example.recipeadviser.network

import com.squareup.moshi.Json

data class UpdatedRecipesInfo(
        val recipe: List<UpdatedRecipes> = listOf(),
        @Json(name = "recipe_to_ingredient") val recipeToIngredient: List<UpdatedRecipeToIngredient> = listOf(),
        val ingredient: List<UpdatedIngredient> = listOf(),
        val step: List<UpdatedStep> = listOf()
)

data class UpdatedRecipes(
        val id: String,
        @Json(name = "recipe_name") val recipeName: String
)

data class UpdatedRecipeToIngredient(
        val id: String,
        @Json(name = "recipe_id") val recipeId: String,
        @Json(name = "ingredient_id") val ingredientId: String
)

data class UpdatedIngredient(
        @Json(name = "ingredient_id") val ingredientId: String,
        val name: String,
        val amount: Double,
        val measure: String,
        val type: String
)

data class UpdatedStep(
    @Json(name = "recipe_id") val recipeId: String,
    val number: Int,
    val description: String
)
