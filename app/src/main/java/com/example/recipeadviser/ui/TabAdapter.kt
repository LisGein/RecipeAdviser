package com.example.recipeadviser.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recipeadviser.ui.fragments.CurrentIngredients
import com.example.recipeadviser.ui.fragments.CurrentSteps

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        if (position == 0)
        {
            return CurrentIngredients()
        }
        return CurrentSteps()
    }
}