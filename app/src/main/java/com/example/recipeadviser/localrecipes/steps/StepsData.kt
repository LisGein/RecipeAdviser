package com.example.recipeadviser.localrecipes.steps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "steps", indices = arrayOf(Index(value = ["recipe_id"]), Index(value = ["number"])))

class StepsData(@PrimaryKey @ColumnInfo(name = "number") val number: Int,
                @ColumnInfo(name = "recipe_id") val recipe_id: String,
                @ColumnInfo(name = "description") val description: String)
{}
