package com.example.recipeadviser.ui.productlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipeadviser.localdatabase.RecipeRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductListViewModelFactory(private val mApplication: Application) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(mApplication) as T
    }
}

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductListIngredientsRepository
    init {
        val dataDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDataDao()
        repository = ProductListIngredientsRepository(dataDao)
    }

    suspend fun addCheckedIngredient(ingredientInfo: ProductListIngredientInfo)
    {
        repository.insertIngredient(ingredientInfo)
    }

    fun removeAll()
    {
        GlobalScope.launch {
            repository.removeAllIngredients()
        }
    }

    fun find(id: String, name: String): List<ProductListIngredientInfo>
    {
        return runBlocking {
            //var all = repository.getAll()

            repository.getProductListIngredientInfo(id, name)
        }
    }

    fun getAll(): MutableList<ProductListIngredientInfo>
    {
        return runBlocking {
            //var all = repository.getAll()

            repository.getAll()
        }
    }
}