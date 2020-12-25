package com.example.recipeadviser.ui.productlist

import com.example.recipeadviser.localdatabase.DataDao

class ProductListIngredientsRepository(private val dataDao: DataDao) {

    suspend fun insertIngredient(data: ProductListIngredientInfo) {
        dataDao.insertProductListIngredient(data)
        val list = getAll()
        var name = ""
        for (l in list) {
            if (l.state)
                name += l.name + " "
        }
        println(( if (data.state) "ins - " else "rem - ") + data.name!!)
        println("list of product set:" + name)
    }

    suspend fun removeAllIngredients() {
        dataDao.deleteAllProductListIngredients()
    }

    suspend fun getProductListIngredientInfo(id: String, name: String) : List<ProductListIngredientInfo>{
        return dataDao.getProductListIngredientInfo(id, name)
    }

    suspend fun getAll(): MutableList<ProductListIngredientInfo> {
        return dataDao.getSortedProductListIngredients()
    }

}