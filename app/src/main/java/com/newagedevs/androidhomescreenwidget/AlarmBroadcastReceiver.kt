package com.newagedevs.androidhomescreenwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.widget.RemoteViews
import android.widget.Toast
import java.util.*



class AlarmBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            this.setAlarm(context)
        }


        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PeriSecure:MyWakeLock")
        wl.acquire(10*60*1000L /*10 minutes*/)

        // Put here YOUR code.

        val random = Random()
        val randomInt = random.nextInt(60000)
        val lastUpdate = "R: $randomInt"
        val view = RemoteViews("com.newagedevs.androidhomescreenwidget", R.layout.app_widget)
        val pending = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)
        view.setTextViewText(R.id.widget_current_update, lastUpdate + Calendar.getInstance().time.toString().subSequence(10, 19))
        view.setOnClickPendingIntent(R.id.widget_current_update, pending)
        val theWidget = ComponentName(context, AppWidget::class.java)
        val manager = AppWidgetManager.getInstance(context)
        manager.updateAppWidget(theWidget, view)

        Toast.makeText(context, "Alarm Received", Toast.LENGTH_LONG).show() // For example
        wl.release()
    }

    fun setAlarm(context: Context) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, AlarmBroadcastReceiver::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)
        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            (1000 * 60 * 1).toLong(),
            pi
        )
    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
    }

}