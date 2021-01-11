package com.example.recipeadviser.ui.recipesteps

import android.os.CountDownTimer
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.recipeadviser.localdatabase.steps.TimerData
import com.example.recipeadviser.ui.fragments.TimerMessageDialog
import java.util.*
import java.util.concurrent.TimeUnit

class Timer(private var timeLeft: TextView?, private val fm: FragmentManager) {
    enum class TimerState{
        Paused, Running
    }
    private var timerLength: Long = 0

    private var timerState = TimerState.Paused

    private var msRemaining: Long = timerLength
    private lateinit var timer: CountDownTimer

    var message: String = ""

    fun initTimer(timerData: TimerData) {
        if (timerLength != timerData.duration) {
            timerLength = timerData.duration
            msRemaining = timerLength
            message = timerData.message
        }
        updateCountdownUI()
    }

    fun getStatus() : TimerState {
        return timerState
    }

    fun updateCountdownUI() {
        val hours = TimeUnit.MILLISECONDS.toHours(msRemaining)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(msRemaining)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(msRemaining)
        timeLeft?.text = String.format(
            Locale.US,
            "%02d:%02d:%02d",
            hours,
            minutes - TimeUnit.HOURS.toMinutes(hours),
            seconds - TimeUnit.MINUTES.toSeconds(minutes)
        )
    }

    fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(msRemaining, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                msRemaining = millisUntilFinished
                updateCountdownUI()
            }

        }.start()
    }

    fun onTimerFinished() {
        timerState = TimerState.Paused
        TimerMessageDialog(message).show(fm, TimerMessageDialog.TAG)
    }

    fun pause() {
        timerState = TimerState.Paused
        timer.cancel()
    }

    fun reset() {
        timer.cancel()
        msRemaining = timerLength
        updateCountdownUI()
        if (timerState == TimerState.Running)
            startTimer()
    }
}