package com.example.recipeadviser.localrecipes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDataDao {

    @Query("SELECT * FROM essential_recipe_data_table ORDER BY id ASC")
    fun getSortedData(): LiveData<List<RecipeData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(RecipeData: RecipeData)

    @Query("DELETE FROM essential_recipe_data_table")
    suspend fun deleteAll()
}