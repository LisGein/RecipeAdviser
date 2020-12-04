package com.example.recipeadviser.ui

import android.content.Intent
import com.example.recipeadviser.ui.activities.MainActivity
import com.example.recipeadviser.SerializableIngredients
import com.example.recipeadviser.SerializableStep
import com.example.recipeadviser.convertToSerializableIngredients
import com.example.recipeadviser.localrecipes.essential.RecipeViewModel
import com.example.recipeadviser.ui.activities.CurrentRecipeActivity

class SelectItemListener(
        private var dataViewModel: RecipeViewModel,
        private val mainActivity: MainActivity)
{
    private val newWordActivityRequestCode = 1
    fun onClickListener(recipe_id: String)
    {
        val intent = Intent(mainActivity, CurrentRecipeActivity::class.java)

        val recipeData = dataViewModel.getRecipeData(recipe_id)
        intent.putExtra("name", recipeData.recipeName)

        val ingredients = dataViewModel.getIngredients(recipe_id)
        val ingrs : ArrayList<SerializableIngredients> = arrayListOf()
        for (i in ingredients)
        {
            ingrs.add(convertToSerializableIngredients(i))
        }
        intent.putParcelableArrayListExtra("ingredients", ingrs)


        val steps = dataViewModel.getSteps(recipe_id)
        val serializableSteps : ArrayList<SerializableStep> = arrayListOf()
        for (s in steps)
        {
            serializableSteps.add(SerializableStep(s.recipe_id, s.number, s.description))
        }
        intent.putParcelableArrayListExtra("serializableSteps", serializableSteps)


        mainActivity.startActivityForResult(intent, newWordActivityRequestCode)
    }
}