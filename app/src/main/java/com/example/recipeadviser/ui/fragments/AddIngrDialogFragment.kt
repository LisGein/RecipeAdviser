package com.example.recipeadviser.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel
import com.example.recipeadviser.localdatabase.essential.RecipeViewModelFactory
import com.example.recipeadviser.localdatabase.ingredients.UserIngredient
import kotlinx.coroutines.launch

class AddIngrDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val v =  inflater.inflate(R.layout.dialog_add_ingredient, null)
            val ingrNameTextView: EditText = v.findViewById(R.id.name_of_ingr)
            val measureTextView: EditText = v.findViewById(R.id.measure_of_ingr)
            val unitTextView: EditText = v.findViewById(R.id.unit_of_measure)
            val typeTextView: EditText = v.findViewById(R.id.type_of_ingr)


            builder.setView(v)
                .setPositiveButton(R.string.ok
                ) { dialog, id ->

                    val recipeViewModel: RecipeViewModel by activityViewModels{ RecipeViewModelFactory(requireActivity().application) }

                    val data = UserIngredient(ingrNameTextView.text.toString(), measureTextView.text.toString().toDouble(), unitTextView.text.toString(), typeTextView.text.toString())

                    recipeViewModel.viewModelScope.launch {
                        recipeViewModel.insertUserIngredient(data)
                    }

                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    companion object {
        const val TAG = "Input ingredient dialog"
    }
}