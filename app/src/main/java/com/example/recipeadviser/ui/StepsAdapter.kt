package com.example.recipeadviser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableStep

class StepsAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = arrayListOf<SerializableStep>()  // Cached copy of recipes

    inner class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepDescription: TextView = itemView.findViewById(R.id.step_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val itemView = inflater.inflate(R.layout.current_recipe_step_item, parent, false)
        return StepsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val current = data[position]
        holder.stepDescription.text = current.description

    }

    internal fun setSteps(data: ArrayList<SerializableStep>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}