package com.example.recipeadviser.ui.productlist

import com.example.recipeadviser.localdatabase.DataDao

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