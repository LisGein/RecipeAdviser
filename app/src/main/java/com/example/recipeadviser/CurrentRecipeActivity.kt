package com.example.recipeadviser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CurrentRecipeActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_recipe)
        textView = findViewById<TextView>(R.id.name_of_recipe)
        textView.setText(intent.getStringExtra("name"))
    }
}