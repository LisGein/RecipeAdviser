package com.example.recipeadviser.localrecipes.ingredients
/*
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeadviser.localrecipes.RecipeRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IngredientRepository

    val allData: LiveData<List<IngredientData>>

    init {
        val dataDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDataDao()
        repository = IngredientRepository(dataDao)
        allData = repository.allIngredients
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(Data: IngredientData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(Data)
    }

    fun remove(ingredient_id: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.remove(ingredient_id)
    }


}*/