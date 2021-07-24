package com.newagedevs.androidhomescreenwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.os.SystemClock
import android.widget.RemoteViews
import android.widget.Toast
import java.util.*



class Alarm : BroadcastReceiver() {

    private var service: PendingIntent? = null


    override fun onReceive(context: Context, intent: Intent) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PeriSecure:MyWakeLock")
        wl.acquire()

        // Put here YOUR code.

        //context.startService(Intent(context, YourService::class.java))



        val random = Random()
        val randomInt = random.nextInt(60000)
        val lastUpdate = "R: $randomInt"
        // Reaches the view on widget and displays the number

        val view = RemoteViews("com.newagedevs.androidhomescreenwidget", R.layout.app_widget)


        val pending = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)



        view.setTextViewText(R.id.textView, lastUpdate + Calendar.getInstance().time.toString().subSequence(10, 19))
        view.setOnClickPendingIntent(R.id.textView, pending)

        val theWidget = ComponentName(context, AppWidget::class.java)
        val manager = AppWidgetManager.getInstance(context)
        manager.updateAppWidget(theWidget, view)


        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show() // For example
        wl.release()
    }

    fun setAlarm(context: Context) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, Alarm::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)
        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            //60000,
            //(1000 * 60 * 2).toLong(),
            180000,
            pi
        ) // Millisec * Second * Minute


    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, Alarm::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
    }
}