package com.newagedevs.androidhomescreenwidget

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.internal.ContextUtils.getActivity
import java.text.SimpleDateFormat
import java.util.*


fun getCurrentTime(): String {
    val dateObj: Date = Calendar.getInstance().time
    return SimpleDateFormat("K:mm:ss a", Locale.US).format(dateObj).toString()
}

fun showSimpleNotification(context: Context, title: String, message: String): String {

    val notificationManager: NotificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "i.apps.notifications"
    val description = "Widget Notification"

    val intent = Intent(context, MainActivity::class.java)

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationChannel = NotificationChannel(
            channelId,
            description,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.GREEN
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)
        builder = Notification.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            .setContentIntent(pendingIntent)
    } else {
        builder = Notification.Builder(context)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            .setContentIntent(pendingIntent)
    }
    notificationManager.notify(1234, builder.build())

    return "Success"
}
