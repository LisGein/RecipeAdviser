package com.example.recipeadviser.ui.recipesteps

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeadviser.R
import com.example.recipeadviser.localdatabase.steps.StepsData
import pl.hypeapp.materialtimelineview.MaterialTimelineView


class StepsAdapter(private val context: Context, private val data: List<StepsData>) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepDescription: TextView? = itemView.findViewById(R.id.step_description)
        val timelineItemView: MaterialTimelineView = itemView.findViewById(R.id.item_steps_timeline)
        val startTimer: ImageButton? = itemView.findViewById(R.id.start_timer)
        val restartTimer: ImageButton? = itemView.findViewById(R.id.restart_timer)
        val timeLeft: TextView? = itemView.findViewById(R.id.time_left)
        val timer = Timer(timeLeft)

        init {
            startTimer?.setVisibility(View.VISIBLE)
            restartTimer?.setVisibility(View.VISIBLE)
            timeLeft?.setVisibility(View.VISIBLE)

            val res: Resources = context.getResources()
            startTimer?.setOnClickListener {
                if (timer.getStatus() == Timer.TimerState.Paused) {
                    startTimer.setImageDrawable(
                        res.getDrawable(
                            R.drawable.ic_baseline_pause_circle_24,
                            null
                        )
                    )
                    timer.startTimer()
                }
                else {
                    startTimer.setImageDrawable(res.getDrawable(R.drawable.ic_baseline_play_arrow_24, null))
                    timer.pause()
                }
            }
            restartTimer?.setOnClickListener {
                timer.reset()
            }
        }
    }

    fun hideTimer(holder: StepsViewHolder) {
        holder.startTimer?.setVisibility(View.INVISIBLE)
        holder.restartTimer?.setVisibility(View.INVISIBLE)
        holder.timeLeft?.setVisibility(View.INVISIBLE)
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
        if (current.timer == null ||  current.timer.duration <= 0) {
            hideTimer(holder)
        }
        else {
            holder.startTimer?.setVisibility(View.VISIBLE)
            holder.restartTimer?.setVisibility(View.VISIBLE)
            holder.timeLeft?.setVisibility(View.VISIBLE)
            holder.timer.initTimer(current.timer)
        }
    }

    override fun getItemCount(): Int {
        // Elements with even numbers will be represented by line connections
        return data.size * 2 - 1
    }

    override fun getItemViewType(position: Int) = if (position % 2 == 0)
        MaterialTimelineView.TIMELINE_TYPE_ITEM else MaterialTimelineView.TIMELINE_TYPE_LINE

}