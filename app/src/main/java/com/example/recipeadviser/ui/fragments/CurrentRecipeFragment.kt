package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel
import com.example.recipeadviser.localdatabase.essential.RecipeViewModelFactory
import com.example.recipeadviser.ui.TabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CurrentRecipeFragment : Fragment() {
    private lateinit var textView: TextView

    private lateinit var adapter: TabAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_recipe, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById<TextView>(R.id.name_of_recipe)

        adapter = TabAdapter(this)
        viewPager = view.findViewById(R.id.viewpager)
        viewPager.adapter = adapter


        val dataViewModel: RecipeViewModel by activityViewModels{
            RecipeViewModelFactory(
                requireActivity().application
            )
        }

        val recipeData = dataViewModel.getRecipeData(dataViewModel.getCurrentRecipeId())
        textView.setText(recipeData.recipeName)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = context?.getText(R.string.ingredients)
                1 -> tab.text = context?.getText(R.string.preparing_steps)
                else -> tab.text = "undefined"
            }
        }.attach()
    }
}