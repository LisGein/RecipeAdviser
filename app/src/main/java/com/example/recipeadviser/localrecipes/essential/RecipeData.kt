package com.example.recipeadviser.localrecipes.essential

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class RecipeData(@PrimaryKey @ColumnInfo(name = "id") val recipe_id: String,
                 @ColumnInfo(name = "recipe_name") val recipe_name: String) {
}

