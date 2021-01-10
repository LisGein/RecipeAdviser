package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.ui.IngredientsAdapter
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel
import com.example.recipeadviser.localdatabase.essential.RecipeViewModelFactory

class CurrentIngredients: Fragment()
{
    private val dataViewModel: RecipeViewModel by activityViewModels{ RecipeViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_ingredients, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingrRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_ingredients)
        ingrRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = IngredientsAdapter(requireContext(), dataViewModel.getIngredients(dataViewModel.getCurrentRecipeId()))
        ingrRecyclerView.adapter = adapter
    }
}