package com.example.recipeadviser

import com.example.recipeadviser.localrecipes.RecipeViewModel

class RemoveItemListener internal constructor(
        private var dataViewModel: RecipeViewModel)
{
    fun onClickListener(recipe_id: String)
    {
        dataViewModel.remove(recipe_id)
    }
}