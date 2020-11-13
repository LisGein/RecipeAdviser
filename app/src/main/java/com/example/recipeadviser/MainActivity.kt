package com.example.recipeadviser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.ui.RecipeDataListAdapter
import com.example.recipeadviser.localrecipes.essential.RecipeViewModel
import com.example.recipeadviser.ui.RemoveItemListener
import com.example.recipeadviser.ui.SelectItemListener

class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel: RecipeViewModel

    private lateinit var removeItemListener: RemoveItemListener
    private lateinit var selectItemListener: SelectItemListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(RecipeViewModel::class.java)

        removeItemListener = RemoveItemListener(dataViewModel)
        selectItemListener = SelectItemListener(dataViewModel, this)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeDataListAdapter(this, removeItemListener, selectItemListener)
        recyclerView.adapter = adapter


        dataViewModel.allData.observe(this){ data ->
            // Update the cached copy of the data in the adapter.
            data?.let { adapter.setRecipes(it) }
        }

    }
}
