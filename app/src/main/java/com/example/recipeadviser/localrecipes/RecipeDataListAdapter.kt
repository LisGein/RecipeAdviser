package com.example.recipeadviser.localrecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.RemoveItemListener

class RecipeDataListAdapter internal constructor(
        context: Context,
        private val cellClickListener: RemoveItemListener
) : RecyclerView.Adapter<RecipeDataListAdapter.RecipeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = emptyList<RecipeData>() // Cached copy of recipes

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeItemView: TextView = itemView.findViewById(R.id.textView)
        val removeBtn: ImageButton = itemView.findViewById(R.id.removeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current = data[position]
        holder.recipeItemView.text = current.recipe_name
        holder.removeBtn.setOnClickListener {
            cellClickListener.onCellClickListener(current.recipe_id)
        }
    }

    internal fun setRecipes(data: List<RecipeData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}