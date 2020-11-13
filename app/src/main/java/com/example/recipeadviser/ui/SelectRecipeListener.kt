package com.example.recipeadviser.ui

import android.content.Intent
import com.example.recipeadviser.MainActivity
import com.example.recipeadviser.SerializableIngredients
import com.example.recipeadviser.localrecipes.essential.RecipeViewModel

class SelectItemListener constructor(
        private var dataViewModel: RecipeViewModel,
        private val main_activity: MainActivity)
{
    private val newWordActivityRequestCode = 1
    fun onClickListener(recipe_id: String)
    {
        val intent = Intent(main_activity, CurrentRecipeActivity::class.java)

        val recipe_data = dataViewModel.get_recipe_data(recipe_id)
        intent.putExtra("name", recipe_data.recipe_name)

        val ingredients = dataViewModel.get_ingredients(recipe_id)
        var ingrs : ArrayList<SerializableIngredients> = arrayListOf()
        for (i in ingredients)
        {
            ingrs.add(SerializableIngredients(i.ingredient_id, i.name, i.amount, i.type))
        }
        intent.putParcelableArrayListExtra("ingredients", ingrs)


        main_activity.startActivityForResult(intent, newWordActivityRequestCode)
    }
}