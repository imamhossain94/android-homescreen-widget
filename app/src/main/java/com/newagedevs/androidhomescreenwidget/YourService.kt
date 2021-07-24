package com.newagedevs.androidhomescreenwidget

import android.content.Intent
import android.os.IBinder
import android.app.Service


class YourService : Service() {
    var alarm = Alarm()
    override fun onCreate() {
        //alarm.setAlarm(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        alarm.setAlarm(this)

        // generates random number


        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        alarm.setAlarm(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}