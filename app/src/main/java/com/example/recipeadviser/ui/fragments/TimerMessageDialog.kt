package com.example.recipeadviser.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.recipeadviser.R

class TimerMessageDialog(private val timerMessage: String): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val v =  inflater.inflate(R.layout.dialog_timer_messsage, null)
            val timerText: TextView = v.findViewById(R.id.timer_message)
            timerText.setText(timerMessage)

            builder.setView(v).setPositiveButton(R.string.ok) { _, _ -> }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    companion object {
        const val TAG = "Timer"
    }
}