package com.newagedevs.androidhomescreenwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


class AppWidget : AppWidgetProvider() {



    private val menuClick = "menuClick"
    private val manualUpdateClick = "manualUpdateClick"



    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        //Update all the widget.
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    override fun onReceive(context: Context?, intent: Intent) {
        super.onReceive(context!!, intent)

        val views = RemoteViews(context.packageName, R.layout.app_widget)

        if (manualUpdateClick == intent.action) {

            views.setTextViewText(R.id.widget_last_update, PrefManager(context).getLastUpdate())
            views.setTextViewText(R.id.widget_update_type, "Manual")
            views.setTextViewText(R.id.widget_current_update, getCurrentTime())
            views.setTextViewText(R.id.widget_total_manual_update,  PrefManager(context).getManualUpdate().toString())
            views.setTextViewText(R.id.widget_total_auto_update,  PrefManager(context).getAutoUpdate().toString())

            PrefManager(context).setLastUpdate(PrefManager(context).getCurrentUpdate())
            PrefManager(context).setCurrentUpdate(getCurrentTime())
            PrefManager(context).setManualUpdate()

            showSimpleNotification(context, "Manual Update", "Home Screen Widget Manually Updated at ${getCurrentTime()}")

            AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, AppWidget::class.java), views)
        }

    }

    private fun getPendingSelfIntent(context: Context?, action: String?): PendingIntent? {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }


    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.app_widget)


        // Button click listener
        views.setOnClickPendingIntent(
            R.id.widget_menu,
            PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                0
            )
        );
        views.setOnClickPendingIntent(
            R.id.widget_manual_update_button, getPendingSelfIntent(
                context,
                manualUpdateClick
            )
        );


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }


}
