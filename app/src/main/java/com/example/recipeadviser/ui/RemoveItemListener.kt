package com.example.recipeadviser.ui

import com.example.recipeadviser.localdatabase.essential.RecipeViewModel

class RemoveItemListener internal constructor(
        private var dataViewModel: RecipeViewModel)
{
    fun onClickListener(recipe_id: String)
    {
        dataViewModel.remove(recipe_id)
    }
}