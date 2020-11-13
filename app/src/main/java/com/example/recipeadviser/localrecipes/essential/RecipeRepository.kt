package com.example.recipeadviser.localrecipes.essential

import androidx.lifecycle.LiveData
import com.example.recipeadviser.localrecipes.DataDao
import com.example.recipeadviser.localrecipes.ingredients.IngredientData

class RecipeRepository(private val dataDao: DataDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRecipes: LiveData<List<RecipeData>> = dataDao.get_sorted_recipes()

    suspend fun insert(Data: RecipeData) {
        dataDao.insert_recipe(Data)
    }

    suspend fun remove(recipe_id: String) {
        dataDao.remove_recipe(recipe_id)
    }

    suspend fun get_recipe_data(recipe_id: String) : RecipeData {
        val data = dataDao.get_recipe_data(recipe_id)
        return data.get(0)
    }

    suspend fun get_ingredients(recipe_id: String) : List<IngredientData> {
        val data = dataDao.get_ingredients_for_recipe(recipe_id)
        return data
    }
}