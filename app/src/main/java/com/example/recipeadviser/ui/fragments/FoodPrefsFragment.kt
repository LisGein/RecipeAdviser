package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.FilterData
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel
import com.example.recipeadviser.localdatabase.essential.RecipeViewModelFactory


class FoodPrefsFragment : Fragment() {

    private val dataViewModel: RecipeViewModel by activityViewModels{ RecipeViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_prefs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createListener(view, R.id.toggle_liver_disease)
        createListener(view, R.id.toggle_low_calorie)
        createListener(view, R.id.toggle_vegetarian)
    }

    private fun createListener(view: View, id: Int)
    {
        val button: ToggleButton = view.findViewById(id)
        val name = button.tag.toString()
        val filterData = dataViewModel.getFilter(name)
        if (filterData != null)
        {
            button.isChecked = filterData.is_checked
        }

        button.setOnCheckedChangeListener { _, isChecked ->
            dataViewModel.insertNewFilter(FilterData(name, isChecked))
        }
    }

}