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


class AlarmBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            this.setAlarm(context)
        }

        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PeriSecure:MyWakeLock")
        wakeLock.acquire(10*60*1000L /*10 minutes*/)

        // Put here YOUR code.
        updateAppWidget(context)

        wakeLock.release()
    }

    fun setAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), (1000 * 60 * 1).toLong(), pendingIntent)

        Toast.makeText(context, "Alarm Service Started!!", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
        Toast.makeText(context, "Alarm Service Canceled!!", Toast.LENGTH_SHORT).show()
    }


    private fun updateAppWidget(context: Context) {

        val views = RemoteViews(context.packageName, R.layout.app_widget)

        views.setTextViewText(R.id.widget_last_update, PrefManager(context).getLastUpdate())
        views.setTextViewText(R.id.widget_update_type, "Auto")
        views.setTextViewText(R.id.widget_current_update, getCurrentTime())
        views.setTextViewText(R.id.widget_total_manual_update,  PrefManager(context).getManualUpdate().toString())
        views.setTextViewText(R.id.widget_total_auto_update,  PrefManager(context).getAutoUpdate().toString())

        PrefManager(context).setLastUpdate(PrefManager(context).getCurrentUpdate())
        PrefManager(context).setCurrentUpdate(getCurrentTime())
        PrefManager(context).setAutoUpdate()

        showSimpleNotification(context, "Auto Update", "Home Screen Widget Auto Updated at ${getCurrentTime()}")

        AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, AppWidget::class.java), views)

    }

}