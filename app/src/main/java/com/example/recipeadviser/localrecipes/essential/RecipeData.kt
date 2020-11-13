package com.example.recipeadviser.localrecipes.essential

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class RecipeData(@PrimaryKey @ColumnInfo(name = "id") val recipeId: String,
                 @ColumnInfo(name = "recipe_name") val recipeName: String) {
}

