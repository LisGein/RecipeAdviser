package com.example.recipeadviser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableIngredients

class CurrentRecipeActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_recipe)
        textView = findViewById<TextView>(R.id.name_of_recipe)
        textView.setText(intent.getStringExtra("name"))

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerview_ingredients)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        val adapter2 = IngredientsAdapter(this)
        recyclerView2.adapter = adapter2

        val ings = intent.getParcelableArrayListExtra<SerializableIngredients>("ingredients")
        if (ings != null) {
            adapter2.setIngredients(ings)
        }
    }
}