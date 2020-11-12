package com.example.recipeadviser.localrecipes

import androidx.lifecycle.LiveData

class RecipeRepository(private val dataDao: RecipeDataDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRecipes: LiveData<List<RecipeData>> = dataDao.getSortedData()

    suspend fun insert(Data: RecipeData) {
        dataDao.insert(Data)
    }

    suspend fun remove(recipe_id: String) {
        dataDao.remove(recipe_id)
    }
}