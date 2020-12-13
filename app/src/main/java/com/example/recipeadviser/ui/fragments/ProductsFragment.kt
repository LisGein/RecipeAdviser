package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeViewModel
import com.example.recipeadviser.localdatabase.essential.RecipeViewModelFactory
import com.example.recipeadviser.ui.productlist.ProductListAdapter
import com.example.recipeadviser.ui.productlist.ProductListViewModel
import com.example.recipeadviser.ui.productlist.ProductListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by activityViewModels{ RecipeViewModelFactory(requireActivity().application) }
    private val productsViewModel: ProductListViewModel by activityViewModels{ ProductListViewModelFactory(requireActivity().application) }

    var adapter: ProductListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expandableProductList = view.findViewById<ExpandableListView>(R.id.expandable_product_list)

        adapter = this.context?.let { ProductListAdapter(it, recipeViewModel.getAllIngredients(), recipeViewModel.getUserIngredients(), viewLifecycleOwner, productsViewModel) }
        expandableProductList.setAdapter(adapter)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_add_ingr)
        fab.setOnClickListener {
            AddIngrDialogFragment().show(
                childFragmentManager, AddIngrDialogFragment.TAG)
        }
    }
}
