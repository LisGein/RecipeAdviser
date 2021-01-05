package com.example.recipeadviser.ui.recipesteps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.SerializableStep
import pl.hypeapp.materialtimelineview.MaterialTimelineView

class StepsAdapter(context: Context) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = arrayListOf<SerializableStep>()  // Cached copy of steps

    inner class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepDescription: TextView? = itemView.findViewById(R.id.step_description)
        val timelineItemView: MaterialTimelineView = itemView.findViewById(R.id.item_steps_timeline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val itemView = when (viewType) {
            MaterialTimelineView.TIMELINE_TYPE_ITEM -> inflater.inflate(
                R.layout.current_recipe_step_item,
                parent,
                false
            )
            else -> inflater.inflate(R.layout.current_recipe_step_line, parent, false)
        }

        return StepsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val current = data[position / 2]
        holder.timelineItemView.position = when (position) {
            0 -> MaterialTimelineView.POSITION_FIRST
            getItemCount() - 1 -> MaterialTimelineView.POSITION_LAST
            else -> MaterialTimelineView.POSITION_MIDDLE
        }

        val viewType = getItemViewType(position)
        holder.timelineItemView.timelineType = viewType
        if (viewType == MaterialTimelineView.TIMELINE_TYPE_ITEM) {
            holder.stepDescription?.text = current.description
        }
    }

    fun setSteps(data: ArrayList<SerializableStep>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        // Elements with even numbers will be represented by line connections
        return data.size * 2 - 1
    }

    override fun getItemViewType(position: Int) = if (position % 2 == 0)
        MaterialTimelineView.TIMELINE_TYPE_ITEM else MaterialTimelineView.TIMELINE_TYPE_LINE

}