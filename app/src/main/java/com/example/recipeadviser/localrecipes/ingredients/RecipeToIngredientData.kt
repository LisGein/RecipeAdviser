package com.example.recipeadviser.localrecipes.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_ingredient", indices = arrayOf(Index(value = ["recipe_id"]), Index(value = ["ingredient_id"])))

class RecipeToIngredientData(@PrimaryKey @ColumnInfo(name = "id") val id: String,
                     @ColumnInfo(name = "recipe_id") val recipeId: String,
                     @ColumnInfo(name = "ingredient_id") val ingredientId: String){
}
