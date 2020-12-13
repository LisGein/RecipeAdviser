package com.example.recipeadviser.localdatabase.ingredients

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_ingredient")
class UserIngredient(@PrimaryKey @ColumnInfo(name = "name") val name: String,
                     @ColumnInfo(name = "amount") var amount: Double,
                     @ColumnInfo(name = "measure") val measure: String,
                     @ColumnInfo(name = "type") val type: String)
{
}
