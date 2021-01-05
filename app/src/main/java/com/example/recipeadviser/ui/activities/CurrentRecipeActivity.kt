package com.example.recipeadviser.ui.activities

import com.example.recipeadviser.ui.IngredientsAdapter
import com.example.recipeadviser.ui.recipesteps.StepsAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableIngredients
import com.example.recipeadviser.SerializableStep

class CurrentRecipeActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_recipe)
        textView = findViewById<TextView>(R.id.name_of_recipe)
        textView.setText(intent.getStringExtra("name"))

        val ingredients = intent.getParcelableArrayListExtra<SerializableIngredients>("ingredients")
        if (ingredients != null) {
            initIngredients(ingredients)
        }

        val steps = intent.getParcelableArrayListExtra<SerializableStep>("serializableSteps")
        if (steps != null) {
            initSteps(steps)
        }
    }

    private fun initIngredients(ingredients : ArrayList<SerializableIngredients>) {
        val ingrRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_ingredients)
        ingrRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = IngredientsAdapter(this)
        ingrRecyclerView.adapter = adapter

        adapter.setIngredients(ingredients)
    }

    private fun initSteps(steps : ArrayList<SerializableStep>) {
        val stepsRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_steps)
        stepsRecyclerView.layoutManager = LinearLayoutManager(this)
        val stepsAdapter = StepsAdapter(this)
        stepsRecyclerView.adapter = stepsAdapter

        stepsAdapter.setSteps(steps)
    }
}