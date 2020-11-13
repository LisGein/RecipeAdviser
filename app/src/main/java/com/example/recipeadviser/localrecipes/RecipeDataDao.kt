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

    @Query("DELETE FROM essential_recipe_data_table WHERE id = :recipe_id")
    suspend fun remove(recipe_id: String)

    @Query("SELECT recipe_name, id FROM essential_recipe_data_table WHERE id = :recipe_id")
    suspend fun get_recipe_name(recipe_id: String): List<RecipeData>
}