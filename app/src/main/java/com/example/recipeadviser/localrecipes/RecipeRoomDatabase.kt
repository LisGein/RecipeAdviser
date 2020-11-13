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
                    dao.insert_recipe(recipe)

                    recipe = RecipeData("1", "Bowl")
                    dao.insert_recipe(recipe)

                    recipe = RecipeData("2", "Fish")
                    dao.insert_recipe(recipe)

                    dao.insert_ingredient(IngredientData("1", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("0", "0", "1"))

                    dao.insert_ingredient(IngredientData("2", "Bread", "100 ml", "2"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("1", "0", "2"))

                    dao.insert_ingredient(IngredientData("3", "Soy", "100 ml", "3"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("2", "0", "3"))

                    dao.insert_ingredient(IngredientData("4", "Meat", "100 ml", "4"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("3", "0", "4"))


                    dao.insert_ingredient(IngredientData("5", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("4", "1", "5"))

                    dao.insert_ingredient(IngredientData("6", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("5", "1", "6"))

                    dao.insert_ingredient(IngredientData("7", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("6", "1", "7"))


                    dao.insert_ingredient(IngredientData("8", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("7", "2", "8"))

                    dao.insert_ingredient(IngredientData("9", "Milk", "100 ml", "1"))
                    dao.insert_recipe_to_ingredients(RecipeToIngredientData("8", "2", "9"))
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
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                    ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                                context.applicationContext,
                                RecipeRoomDatabase::class.java,
                                "recipe_essential_database"
                        )
                                .addCallback(DataDatabaseCallback(scope))
                                .build()
                        INSTANCE = instance
                        // return instance
                        instance
                    }
        }
    }
}