package com.example.recipeadviser.localrecipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "essential_recipe_data_table")
class EssentialRecipeDataTable(@PrimaryKey @ColumnInfo(name = "id") val recipe_id: String,
                               @ColumnInfo(name = "recipe_name") val recipe_name: String){

}