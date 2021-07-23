package com.newagedevs.androidhomescreenwidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build




class AutoStart: BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent) {
        val intent = Intent(context, AppService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }
}