package com.example.recipeadviser.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.UserViewModel
import com.example.recipeadviser.UserViewModelFactory
import com.example.recipeadviser.localrecipes.essential.RecipeViewModel
import com.example.recipeadviser.localrecipes.essential.RecipeViewModelFactory
import com.example.recipeadviser.ui.RecipeDataListAdapter
import com.example.recipeadviser.ui.RemoveItemListener
import com.example.recipeadviser.ui.SelectItemListener
import com.example.recipeadviser.ui.activities.MainActivity
import com.example.recipeadviser.ui.productlist.ProductListViewModel
import com.example.recipeadviser.ui.productlist.ProductListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipeListFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels{ UserViewModelFactory(requireActivity().application) }

    private val dataViewModel: RecipeViewModel by activityViewModels{ RecipeViewModelFactory(requireActivity().application) }

    private val productsViewModel: ProductListViewModel by activityViewModels{ ProductListViewModelFactory(requireActivity().application) }

    private lateinit var removeItemListener: RemoveItemListener
    private lateinit var selectItemListener: SelectItemListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        /*if (userViewModel.sessionManager.fetchAuthToken() == null) {
            navController.navigate(R.id.loginFragment)
        }
*/
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_show_product_list)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_productsFragment)
        }

        val fabUpdate = view.findViewById<FloatingActionButton>(R.id.fab_update)
        fabUpdate.setOnClickListener {context?.let {
            dataViewModel.removeAll()
            productsViewModel.removeAll()
            dataViewModel.updateRecipesList(it)  }
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