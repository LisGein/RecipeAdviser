package com.example.recipeadviser.localdatabase.essential

import androidx.lifecycle.LiveData
import com.example.recipeadviser.localdatabase.DataDao
import com.example.recipeadviser.localdatabase.FilterData
import com.example.recipeadviser.localdatabase.ingredients.IngredientData
import com.example.recipeadviser.localdatabase.ingredients.RecipeToIngredientData
import com.example.recipeadviser.localdatabase.ingredients.UserIngredient
import com.example.recipeadviser.localdatabase.steps.StepsData
import com.example.recipeadviser.ui.productlist.ProductListIngredientInfo

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

    suspend fun removeAllRecipes() {
        dataDao.deleteAllRecipes()
    }

    suspend fun removeAllIngredients() {
        dataDao.deleteAllIngredients()
    }

    suspend fun removeAllSteps() {
        dataDao.deleteAllSteps()
    }

    suspend fun removeAllRecipeToIngredients() {
        dataDao.deleteAllRecipeToIngredients()
    }


    suspend fun getRecipeData(recipe_id: String) : RecipeData {
        val data = dataDao.getRecipeData(recipe_id)
        return data.get(0)
    }

    fun getAllIngredients() : LiveData<List<IngredientData>> {
        return dataDao.getSortedIngredients()
    }

    fun getUserIngredients() : LiveData<List<UserIngredient>> {
        return dataDao.getUserIngredients()
    }

    suspend fun getIngredients(recipe_id: String) : List<IngredientData> {
        val data = dataDao.getIngredientsForRecipe(recipe_id)
        return data
    }

    suspend fun getSteps(recipe_id: String) : List<StepsData> {
        val data = dataDao.getStepsForRecipe(recipe_id)
        return data
    }

    suspend fun insertIngredient(Data: IngredientData) {
        dataDao.insertIngredient(Data)
    }

    suspend fun insertRecipeToIngredients(Data: RecipeToIngredientData) {
        dataDao.insertRecipeToIngredients(Data)
    }

    suspend fun insertStep(Data: StepsData) {
        dataDao.insertStep(Data)
    }

    suspend fun insertUserIngredient(data: UserIngredient) {
        val list = dataDao.getUserIngredient(data.name)
        if (!list.isEmpty())
            data.amount += list.get(0).amount
        dataDao.insertUserIngredient(data)
    }

    suspend fun removeAllProducts() {
        dataDao.deleteAllProductListIngredients()
    }

    suspend fun insertNewFilter(data: FilterData) {
        dataDao.insertNewFilter(data)
    }

    suspend fun getFilter(data: String) : List<FilterData> {
        return dataDao.getFilter(data)
    }


    suspend fun getAllFilters() : List<FilterData> {
        return dataDao.getAllFilters()
    }
}