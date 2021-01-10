package com.example.recipeadviser.ui

import androidx.navigation.NavController
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel

class SelectItemListener(
        private var dataViewModel: RecipeViewModel,
        private val navController: NavController) {

    fun onClickListener(recipe_id: String) {
        dataViewModel.setCurrentRecipeId(recipe_id)
        navController.navigate(R.id.currentRecipeFragment)
    }
}