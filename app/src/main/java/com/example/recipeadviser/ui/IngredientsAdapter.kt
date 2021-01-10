package com.example.recipeadviser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.ingredients.IngredientData

class IngredientsAdapter internal constructor(
        context: Context, private val data : List<IngredientData>
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingrName: TextView = itemView.findViewById(R.id.ingr_name)
        val ingrAmount: TextView = itemView.findViewById(R.id.ingr_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val itemView = inflater.inflate(R.layout.current_recipe_ingr_item, parent, false)
        return IngredientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val current = data[position]
        holder.ingrName.text = current.name
        holder.ingrAmount.setText(current.amount.toString() + current.measure)

    }

    override fun getItemCount() = data.size
}