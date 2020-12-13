package com.example.recipeadviser.localdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "filter")
class FilterData(@PrimaryKey @ColumnInfo(name = "param_name") val param_name: String,
                 @ColumnInfo(name = "is_checked") val is_checked: Boolean)
{
}
