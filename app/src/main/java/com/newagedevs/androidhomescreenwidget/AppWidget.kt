package com.newagedevs.androidhomescreenwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.RemoteViews


/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {

    private var service: PendingIntent? = null

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }


//        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val i = Intent(context, AppService::class.java)
//
//        if (service == null) {
//            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT)
//        }
//        manager.setRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime(),
//            60000,
//            service
//        )

        //Alarm().setAlarm(context);
        Log.d("AppWidget: ", "onUpdate")

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(R.id.textView, "Loading")

    val pending = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)

    views.setOnClickPendingIntent(
        R.id.textView,
        pending
    )

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}




//class AppWidget : AppWidgetProvider() {
//    //private var service: PendingIntent? = null
//    var alarm = Alarm()
//    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
//
////        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
////        val i = Intent(context, YourService::class.java)
////        if (service == null) {
////            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT)
////        }
////        manager.setRepeating(
////            AlarmManager.ELAPSED_REALTIME,
////            SystemClock.elapsedRealtime(),
////            60000,
////            service
////        )
//
////        val intent = Intent(context, YourService::class.java)
////        context.startService(intent)
//
//
//        alarm.setAlarm(context);
//
//        //if you need to call your service less than 60 sec
//        //answer is here:
//        //http://stackoverflow.com/questions/29998313/how-to-run-background-service-after-every-5-sec-not-working-in-android-5-1
//        Log.d("UpdatingWidget: ", "onUpdate")
//    }
//
//    override fun onReceive(context: Context, intent: Intent) {
//        super.onReceive(context, intent)
//        Log.d("UpdatingWidget: ", "onReceive")
//    }
//
//    override fun onAppWidgetOptionsChanged(
//        context: Context,
//        appWidgetManager: AppWidgetManager,
//        appWidgetId: Int,
//        newOptions: Bundle
//    ) {
//        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
//        Log.d("UpdatingWidget: ", "onAppWidgetOptionsChanged")
//    }
//
//    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
//        super.onDeleted(context, appWidgetIds)
//        Log.d("UpdatingWidget: ", "onDeleted")
//    }
//
//    override fun onEnabled(context: Context) {
//        super.onEnabled(context)
//        Log.d("UpdatingWidget: ", "onEnabled")
//    }
//
//    override fun onDisabled(context: Context) {
//        super.onDisabled(context)
//        Log.d("UpdatingWidget: ", "onDisabled")
//    }
//
//    override fun onRestored(context: Context, oldWidgetIds: IntArray, newWidgetIds: IntArray) {
//        super.onRestored(context, oldWidgetIds, newWidgetIds)
//        Log.d("UpdatingWidget: ", "onRestored")
//    }
//}