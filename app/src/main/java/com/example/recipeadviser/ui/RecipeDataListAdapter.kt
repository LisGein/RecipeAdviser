package com.example.recipeadviser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.essential.RecipeData

class RecipeDataListAdapter internal constructor(
        context: Context,
        private val cellClickListener: RemoveItemListener,
        private val selectItemListener: SelectItemListener
) : RecyclerView.Adapter<RecipeDataListAdapter.RecipeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = emptyList<RecipeData>() // Cached copy of recipes

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeItemView: TextView = itemView.findViewById(R.id.textView)
        val removeBtn: ImageButton = itemView.findViewById(R.id.removeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = inflater.inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current = data[position]
        holder.recipeItemView.text = current.recipeName
        holder.removeBtn.setOnClickListener {
            cellClickListener.onClickListener(current.recipeId)
        }
        holder.recipeItemView.setOnClickListener{
            selectItemListener.onClickListener(current.recipeId)
        }
    }

    internal fun setRecipes(data: List<RecipeData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}