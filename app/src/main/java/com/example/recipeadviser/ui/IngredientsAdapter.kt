package com.example.recipeadviser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableIngredients

class IngredientsAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = arrayListOf<SerializableIngredients>()  // Cached copy of recipes

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
        holder.ingrAmount.text = current.amount

    }

    internal fun setIngredients(data: ArrayList<SerializableIngredients>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}