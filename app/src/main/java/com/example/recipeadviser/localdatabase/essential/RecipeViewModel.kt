package com.example.recipeadviser.localdatabase.essential

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.recipeadviser.localdatabase.FilterData
import com.example.recipeadviser.localdatabase.RecipeRoomDatabase
import com.example.recipeadviser.localdatabase.ingredients.IngredientData
import com.example.recipeadviser.localdatabase.ingredients.RecipeToIngredientData
import com.example.recipeadviser.localdatabase.ingredients.UserIngredient
import com.example.recipeadviser.localdatabase.steps.StepsData
import com.example.recipeadviser.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class RecipeViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(mApplication) as T
    }
}

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    // Using LiveData and caching what getSortedData returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allData: LiveData<List<RecipeData>>

    private val _response = MutableLiveData<String>()

    init {
        val dataDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDataDao()
        repository = RecipeRepository(dataDao)
        allData = repository.allRecipes
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertRecipe(data: RecipeData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRecipe(data)
    }

    fun remove(recipeId: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeRecipe(recipeId)
    }


    fun removeAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.removeAllRecipes()
        repository.removeAllIngredients()
        repository.removeAllSteps()
        repository.removeAllRecipeToIngredients()
    }

    fun getRecipeData(recipeId: String) : RecipeData {
        val data = runBlocking{
            repository.getRecipeData(recipeId)
        }
        return data
    }

    fun getAllIngredients() : LiveData<List<IngredientData>> {
        val data = runBlocking{
            repository.getAllIngredients()
        }
        return data
    }

    fun getIngredients(recipeId: String) : List<IngredientData> {
        val data = runBlocking{
            repository.getIngredients(recipeId)
        }
        return data
    }

    suspend fun insertUserIngredient(data: UserIngredient) {
        repository.insertUserIngredient(data)
    }

    fun getUserIngredients() : LiveData<List<UserIngredient>> {
        val data = runBlocking{
            repository.getUserIngredients()
        }
        return data
    }

    fun getSteps(recipeId: String) : List<StepsData> {
        val data = runBlocking{
            repository.getSteps(recipeId)
        }
        return data
    }

    fun insertNewFilter(data: FilterData) {
        viewModelScope.launch {
            repository.insertNewFilter(data)
        }
    }

    fun getFilter(name: String): FilterData? {
        val filters =
        runBlocking {
            repository.getFilter(name)
        }
        return if (filters.isEmpty()) null else filters.get(0)
    }

    fun updateRecipesList(con: Context) {
        viewModelScope.launch {
            try {
                val filters =  repository.getAllFilters()

                val rootObject= JSONObject()
                for (f in filters)
                {
                    rootObject.put(f.param_name,f.is_checked)
                }


                val listResult = ApiClient.getApiService(con).getUpdatedRecipes(rootObject.toString())
                for (recipe in listResult.recipe) {
                    insertRecipe(RecipeData(recipe.id, recipe.recipeName))
                }
                for (recipe in listResult.recipeToIngredient) {
                    repository.insertRecipeToIngredients(
                        RecipeToIngredientData(
                            recipe.id,
                            recipe.recipeId,
                            recipe.ingredientId
                        )
                    )
                }
                for (recipe in listResult.ingredient) {
                    repository.insertIngredient(
                        IngredientData(
                            recipe.ingredientId,
                            recipe.name,
                            recipe.amount,
                            recipe.measure,
                            recipe.type
                        )
                    )
                }
                for (recipe in listResult.step) {
                    repository.insertStep(
                        StepsData(
                            recipe.number,
                            recipe.recipeId,
                            recipe.description
                        )
                    )
                }

                _response.value = "Success"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }

            Toast.makeText(con, _response.value, Toast.LENGTH_SHORT).show()
        }
    }
}