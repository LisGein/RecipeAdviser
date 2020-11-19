package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.ui.activities.MainActivity
import com.example.recipeadviser.R
import com.example.recipeadviser.UserViewModel
import com.example.recipeadviser.localrecipes.essential.RecipeViewModel
import com.example.recipeadviser.ui.RecipeDataListAdapter
import com.example.recipeadviser.ui.RemoveItemListener
import com.example.recipeadviser.ui.SelectItemListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipeListFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var dataViewModel: RecipeViewModel

    private lateinit var removeItemListener: RemoveItemListener
    private lateinit var selectItemListener: SelectItemListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        if (userViewModel._sessionManager?.fetchAuthToken() == null) {
            navController.navigate(R.id.loginFragment)
        }

        if (requireActivity().application != null) {
            dataViewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(
                RecipeViewModel::class.java
            )
        }

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_show_product_list)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_productsFragment)
        }

        val fabUpdate = view.findViewById<FloatingActionButton>(R.id.fab_update)
        fabUpdate.setOnClickListener {
            dataViewModel.removeAll()
            dataViewModel.updateRecipesList(this.context)

        }

        removeItemListener = RemoveItemListener(dataViewModel)
        selectItemListener = SelectItemListener(dataViewModel, requireActivity() as MainActivity)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val adapter = RecipeDataListAdapter(view.context, removeItemListener, selectItemListener)
        recyclerView.adapter = adapter

        dataViewModel.allData.observe(viewLifecycleOwner) { data ->
            // Update the cached copy of the data in the adapter.
            data?.let { adapter.setRecipes(it) }
        }
    }
}