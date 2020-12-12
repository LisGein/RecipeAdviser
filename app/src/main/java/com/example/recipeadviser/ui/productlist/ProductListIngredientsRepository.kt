package com.example.recipeadviser.ui.productlist

import androidx.lifecycle.LiveData
import com.example.recipeadviser.localrecipes.DataDao
import com.example.recipeadviser.localrecipes.essential.RecipeData
import com.example.recipeadviser.localrecipes.ingredients.IngredientData
import com.example.recipeadviser.localrecipes.ingredients.RecipeToIngredientData
import com.example.recipeadviser.localrecipes.steps.StepsData

class ProductListIngredientsRepository(private val dataDao: DataDao) {

    suspend fun insertIngredient(Data: ProductListIngredientInfo) {
        dataDao.insertProductListIngredient(Data)
    }

    suspend fun removeAllIngredients() {
        dataDao.deleteAllProductListIngredients()
    }

    suspend fun getProductListIngredientInfo(id: String) : List<ProductListIngredientInfo>{
        return dataDao.getProductListIngredientInfo(id)
    }
    
}