package com.newagedevs.androidhomescreenwidget

import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import java.util.*


class YourService : Service() {
    var alarm = Alarm()
    override fun onCreate() {
        //alarm.setAlarm(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //alarm.setAlarm(this)

        // generates random number
        val random = Random()
        val randomInt = random.nextInt(60000)
        val lastUpdate = "R: $randomInt"
        // Reaches the view on widget and displays the number

        val view = RemoteViews(packageName, R.layout.app_widget)

        view.setTextViewText(R.id.textView, lastUpdate + Calendar.getInstance().time.toString().subSequence(10, 19))
        view.setOnClickPendingIntent(R.id.textView,
            PendingIntent.getService(applicationContext, 0, Intent(applicationContext, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT))

        val theWidget = ComponentName(this, AppWidget::class.java)
        val manager = AppWidgetManager.getInstance(this)
        manager.updateAppWidget(theWidget, view)

        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        //alarm.setAlarm(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}