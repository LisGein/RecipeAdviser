package com.example.recipeadviser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.recipeadviser.localrecipes.RecipeViewModel

class RemoveItemListener internal constructor(
        private var dataViewModel: RecipeViewModel)
{
    fun onCellClickListener(name: String)
    {
        dataViewModel.remove(name)
    }
}