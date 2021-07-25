package com.newagedevs.androidhomescreenwidget

import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import java.util.*


class AppService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // generates random number
        val random = Random()
        val randomInt = random.nextInt(60000)
        val lastUpdate = "R: $randomInt"
        // Reaches the view on widget and displays the number

        val view = RemoteViews(packageName, R.layout.app_widget)

        view.setTextViewText(R.id.widget_current_update, lastUpdate + Calendar.getInstance().time.toString().subSequence(10, 19))
        view.setOnClickPendingIntent(R.id.widget_current_update,
            PendingIntent.getService(applicationContext, 0, Intent(applicationContext, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT))

        val theWidget = ComponentName(this, AppWidget::class.java)
        val manager = AppWidgetManager.getInstance(this)
        manager.updateAppWidget(theWidget, view)

        return super.onStartCommand(intent, flags, startId)
    }


}
