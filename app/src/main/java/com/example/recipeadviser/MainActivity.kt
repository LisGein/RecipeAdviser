package com.example.recipeadviser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.localrecipes.RecipeDataListAdapter
import com.example.recipeadviser.localrecipes.RecipeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecipeDataListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(RecipeViewModel::class.java)
        dataViewModel.allData.observe(this, Observer { data ->
            // Update the cached copy of the data in the adapter.
            data?.let { adapter.setRecipes(it) }
        })
    }
}