package com.example.recipeadviser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableIngredients
import com.example.recipeadviser.localrecipes.essential.RecipeData
import com.example.recipeadviser.localrecipes.ingredients.IngredientData
import kotlinx.coroutines.runBlocking

class IngredientsAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = arrayListOf<SerializableIngredients>()  // Cached copy of recipes

    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingr_name: TextView = itemView.findViewById(R.id.ingr_name)
        val ingr_amount: TextView = itemView.findViewById(R.id.ingr_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val itemView = inflater.inflate(R.layout.current_recipe_ingr_item, parent, false)
        return IngredientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val current = data[position]
        holder.ingr_name.text = current.name
        holder.ingr_amount.text = current.amount

    }

    internal fun setIngredients(data: ArrayList<SerializableIngredients>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}