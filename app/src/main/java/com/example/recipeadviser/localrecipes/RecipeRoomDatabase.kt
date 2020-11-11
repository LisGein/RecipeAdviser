package com.example.recipeadviser.localrecipes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(RecipeData::class), version = 1, exportSchema = false)
abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDataDao(): RecipeDataDao

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
                    dao.insert(recipe)

                    recipe = RecipeData("1", "Bowl")
                    dao.insert(recipe)

                    recipe = RecipeData("2", "Fish")
                    dao.insert(recipe)
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