package com.newagedevs.androidhomescreenwidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AutoStart: BroadcastReceiver() {
    var alarm = Alarm()
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            alarm.setAlarm(context)
        }
    }
}