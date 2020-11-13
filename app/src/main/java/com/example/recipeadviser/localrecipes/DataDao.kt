package com.example.recipeadviser.localrecipes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeadviser.localrecipes.essential.RecipeData
import com.example.recipeadviser.localrecipes.ingredients.IngredientData
import com.example.recipeadviser.localrecipes.ingredients.RecipeToIngredientData

@Dao
interface DataDao {

    @Query("SELECT ingredient.* FROM ingredient \n" +
            "INNER JOIN recipe_ingredient ON ingredient.id = recipe_ingredient.ingredient_id\n" +
            "WHERE recipe_ingredient.recipe_id=:recipe_id")
    suspend fun getIngredientsForRecipe(recipe_id: String): List<IngredientData>

    @Query("SELECT * FROM recipe ORDER BY id ASC")
    fun getSortedRecipes(): LiveData<List<RecipeData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(RecipeData: RecipeData)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipes()

    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun removeRecipe(recipeId: String)

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeData(recipeId: String): List<RecipeData>

    @Query("SELECT * FROM ingredient ORDER BY id ASC")
    fun getSortedIngredients(): LiveData<List<IngredientData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(IngredientData: IngredientData)

    @Query("DELETE FROM ingredient WHERE id = :ingredientId")
    suspend fun removeIngredient(ingredientId: String)

    @Query("SELECT * FROM recipe_ingredient ORDER BY id ASC")
    fun getSortedRecipeToIngredients(): LiveData<List<RecipeToIngredientData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipeToIngredients(RecipeToIngredientData: RecipeToIngredientData)

    @Query("DELETE FROM recipe_ingredient WHERE id = :id")
    suspend fun removeRecipeToIngredients(id: String)

}