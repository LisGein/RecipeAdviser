package com.example.recipeadviser

import android.content.Intent
import com.example.recipeadviser.localrecipes.RecipeViewModel

class SelectItemListener constructor(
        private var dataViewModel: RecipeViewModel,
        private val main_activity: MainActivity)
{
    private val newWordActivityRequestCode = 1
    fun onClickListener(recipe_id: String)
    {
        val name = dataViewModel.get_recipe_name(recipe_id)
        val intent = Intent(main_activity, CurrentRecipeActivity::class.java).putExtra("name", name)
        main_activity.startActivityForResult(intent, newWordActivityRequestCode)

    }


}