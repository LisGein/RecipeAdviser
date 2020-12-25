package com.example.recipeadviser.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeadviser.localdatabase.essential.RecipeData
import com.example.recipeadviser.localdatabase.ingredients.IngredientData
import com.example.recipeadviser.localdatabase.ingredients.RecipeToIngredientData
import com.example.recipeadviser.localdatabase.ingredients.UserIngredient
import com.example.recipeadviser.localdatabase.steps.StepsData
import com.example.recipeadviser.ui.productlist.ProductListIngredientInfo

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

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeData(recipeId: String): List<RecipeData>

    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun removeRecipe(recipeId: String)

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipes()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStep(StepsData: StepsData)

    @Query("SELECT * FROM steps WHERE recipe_id = :recipeId ORDER BY number ASC")
    suspend fun getStepsForRecipe(recipeId: String): List<StepsData>

    @Query("SELECT * FROM steps ORDER BY recipe_id, number ASC")
    fun getSortedSteps(): LiveData<List<StepsData>>

    @Query("DELETE FROM steps")
    suspend fun deleteAllSteps()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(IngredientData: IngredientData)

    @Query("SELECT * FROM ingredient ORDER BY id ASC")
    fun getSortedIngredients(): LiveData<List<IngredientData>>

    @Query("DELETE FROM ingredient WHERE id = :ingredientId")
    suspend fun removeIngredient(ingredientId: String)

    @Query("DELETE FROM ingredient")
    suspend fun deleteAllIngredients()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipeToIngredients(RecipeToIngredientData: RecipeToIngredientData)

    @Query("SELECT * FROM recipe_ingredient ORDER BY id ASC")
    fun getSortedRecipeToIngredients(): LiveData<List<RecipeToIngredientData>>

    @Query("DELETE FROM recipe_ingredient WHERE id = :id")
    suspend fun removeRecipeToIngredients(id: String)

    @Query("DELETE FROM recipe_ingredient")
    suspend fun deleteAllRecipeToIngredients()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductListIngredient(productListIngredientInfo: ProductListIngredientInfo)

    @Query("SELECT * FROM product_list_ingredient ORDER BY id ASC")
    suspend fun getSortedProductListIngredients(): MutableList<ProductListIngredientInfo>

    @Query("DELETE FROM product_list_ingredient WHERE id = :id")
    suspend fun removeProductListIngredient(id: String)

    @Query("DELETE FROM product_list_ingredient")
    suspend fun deleteAllProductListIngredients()

    @Query("SELECT * FROM product_list_ingredient WHERE id = :ingrId and name = :name")
    suspend fun getProductListIngredientInfo(ingrId: String, name: String): List<ProductListIngredientInfo>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewFilter(data: FilterData)

    @Query("SELECT * FROM filter WHERE param_name = :id")
    suspend fun getFilter(id: String): List<FilterData>

    @Query("SELECT * FROM filter ORDER BY param_name ASC")
    suspend fun getAllFilters(): List<FilterData>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserIngredient(data: UserIngredient)

    @Query("SELECT * FROM user_ingredient WHERE name = :id")
    suspend fun getUserIngredient(id: String): List<UserIngredient>

    @Query("SELECT * FROM user_ingredient ORDER BY name ASC")
    fun getUserIngredients(): LiveData<List<UserIngredient>>

}