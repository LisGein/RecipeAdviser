package com.example.recipeadviser.localrecipes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipeadviser.localrecipes.essential.RecipeData
import com.example.recipeadviser.localrecipes.ingredients.IngredientData
import com.example.recipeadviser.localrecipes.ingredients.RecipeToIngredientData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(IngredientData::class, RecipeToIngredientData::class, RecipeData::class), version = 1, exportSchema = false)
abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDataDao(): DataDao

    private class DataDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val dao = database.recipeDataDao()

                    // Delete all content here.
                    //dao.deleteAll()

                    var recipe = RecipeData("0", "Chicken")
                    dao.insertRecipe(recipe)

                    recipe = RecipeData("1", "Bowl")
                    dao.insertRecipe(recipe)

                    recipe = RecipeData("2", "Fish")
                    dao.insertRecipe(recipe)

                    dao.insertIngredient(IngredientData("1", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("0", "0", "1"))

                    dao.insertIngredient(IngredientData("2", "Bread", "100 ml", "2"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("1", "0", "2"))

                    dao.insertIngredient(IngredientData("3", "Soy", "100 ml", "3"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("2", "0", "3"))

                    dao.insertIngredient(IngredientData("4", "Meat", "100 ml", "4"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("3", "0", "4"))


                    dao.insertIngredient(IngredientData("5", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("4", "1", "5"))

                    dao.insertIngredient(IngredientData("6", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("5", "1", "6"))

                    dao.insertIngredient(IngredientData("7", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("6", "1", "7"))


                    dao.insertIngredient(IngredientData("8", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("7", "2", "8"))

                    dao.insertIngredient(IngredientData("9", "Milk", "100 ml", "1"))
                    dao.insertRecipeToIngredients(RecipeToIngredientData("8", "2", "9"))
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): RecipeRoomDatabase {
            return INSTANCE
                    ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                                context.applicationContext,
                                RecipeRoomDatabase::class.java,
                                "recipe_database"
                        )
                                .addCallback(DataDatabaseCallback(scope))
                                .build()
                        INSTANCE = instance
                        instance
                    }
        }
    }
}