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
    suspend fun get_ingredients_for_recipe(recipe_id: String): List<IngredientData>

    @Query("SELECT * FROM recipe ORDER BY id ASC")
    fun get_sorted_recipes(): LiveData<List<RecipeData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert_recipe(RecipeData: RecipeData)

    @Query("DELETE FROM recipe")
    suspend fun delete_all_recipes()

    @Query("DELETE FROM recipe WHERE id = :recipe_id")
    suspend fun remove_recipe(recipe_id: String)

    @Query("SELECT * FROM recipe WHERE id = :recipe_id")
    suspend fun get_recipe_data(recipe_id: String): List<RecipeData>

    @Query("SELECT * FROM ingredient ORDER BY id ASC")
    fun get_sorted_ingredients(): LiveData<List<IngredientData>>

    @Query("SELECT * FROM recipe_ingredient ORDER BY id ASC")
    fun get_sorted_recipe_to_ingredients(): LiveData<List<RecipeToIngredientData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert_recipe_to_ingredients(RecipeToIngredientData: RecipeToIngredientData)

    @Query("DELETE FROM recipe_ingredient WHERE id = :id")
    suspend fun remove_recipe_to_ingredients(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert_ingredient(IngredientData: IngredientData)

    @Query("DELETE FROM ingredient WHERE id = :ingredient_id")
    suspend fun remove_ingredient(ingredient_id: String)
    
}