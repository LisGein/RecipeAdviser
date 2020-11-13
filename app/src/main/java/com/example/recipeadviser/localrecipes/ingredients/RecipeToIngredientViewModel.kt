package com.example.recipeadviser.localrecipes.ingredients
/*
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeadviser.localrecipes.RecipeRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeToIngredientViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeToIngredientRepository

    val allData: LiveData<List<RecipeToIngredientData>>

    init {
        val dataDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDataDao()
        repository = RecipeToIngredientRepository(dataDao)
        allData = repository.allData
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(Data: RecipeToIngredientData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(Data)
    }

    fun remove(id: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.remove(id)
    }


}*/