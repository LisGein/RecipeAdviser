package com.example.recipeadviser.localrecipes.essential

import androidx.lifecycle.LiveData
import com.example.recipeadviser.localrecipes.DataDao
import com.example.recipeadviser.localrecipes.ingredients.IngredientData
import com.example.recipeadviser.localrecipes.ingredients.RecipeToIngredientData

class RecipeRepository(private val dataDao: DataDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRecipes: LiveData<List<RecipeData>> = dataDao.getSortedRecipes()

    suspend fun insertRecipe(Data: RecipeData) {
        dataDao.insertRecipe(Data)
    }

    suspend fun removeRecipe(recipe_id: String) {
        dataDao.removeRecipe(recipe_id)
    }

    suspend fun removeAllRecipe() {
        dataDao.deleteAllRecipes()
    }

    suspend fun getRecipeData(recipe_id: String) : RecipeData {
        val data = dataDao.getRecipeData(recipe_id)
        return data.get(0)
    }

    suspend fun getIngredients(recipe_id: String) : List<IngredientData> {
        val data = dataDao.getIngredientsForRecipe(recipe_id)
        return data
    }

    suspend fun insertIngredient(Data: IngredientData) {
        dataDao.insertIngredient(Data)
    }

    suspend fun insertRecipeToIngredients(Data: RecipeToIngredientData) {
        dataDao.insertRecipeToIngredients(Data)
    }
}