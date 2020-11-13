package com.example.recipeadviser.localrecipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    // Using LiveData and caching what getSortedData returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allData: LiveData<List<RecipeData>>

    init {
        val dataDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDataDao()
        repository = RecipeRepository(dataDao)
        allData = repository.allRecipes
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(Data: RecipeData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(Data)
    }

    fun remove(recipe_id: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.remove(recipe_id)
    }

    fun get_recipe_name(recipe_id: String) : String {
        val name = runBlocking{
             repository.get_recipe_name(recipe_id)
        }
        return name
    }
}