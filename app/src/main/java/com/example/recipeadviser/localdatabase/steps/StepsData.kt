package com.example.recipeadviser.localdatabase.steps

import androidx.annotation.Nullable
import androidx.room.*

@Entity(tableName = "timer")
class TimerData(@PrimaryKey
    val message: String,
    val duration: Long)
{}

@Entity(tableName = "steps", indices = arrayOf(Index(value = ["recipe_id"]), Index(value = ["number"])))
class StepsData(@PrimaryKey @ColumnInfo(name = "number") val number: Int,
                @ColumnInfo(name = "recipe_id") val recipe_id: String,
                @ColumnInfo(name = "description") val description: String,
                @Embedded val timer: TimerData?)
{}
