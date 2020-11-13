package com.example.recipeadviser.localrecipes.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
class IngredientData(@PrimaryKey @ColumnInfo(name = "id") val ingredient_id: String,
                     @ColumnInfo(name = "name") val name: String,
                     @ColumnInfo(name = "amount") val amount: String,
                     @ColumnInfo(name = "type") val type: String)
{
}

