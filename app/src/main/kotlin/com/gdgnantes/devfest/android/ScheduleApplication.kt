package com.gdgnantes.devfest.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate
import com.gdgnantes.devfest.android.content.RemindersManager
import com.gdgnantes.devfest.android.util.BuildUtils


class ScheduleApplication : Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildUtils.hasO()) {
            createNotificationChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannels() {
        val channel = NotificationChannel(RemindersManager.CHANNEL_REMINDERS,
                getString(R.string.reminders_channel_name),
                NotificationManager.IMPORTANCE_HIGH).apply {
            description = getString(R.string.reminders_channel_description)
            enableLights(true)
            lightColor = ContextCompat.getColor(this@ScheduleApplication, R.color.candy)
            enableVibration(true)
            vibrationPattern = longArrayOf(500, 500, 500, 500)
        }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

}