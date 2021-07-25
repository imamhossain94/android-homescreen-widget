package com.newagedevs.androidhomescreenwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val alarmBroadcastReceiver = AlarmBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startAlarmService = findViewById<TextView>(R.id.start_alarm_service)
        val stopAlarmService = findViewById<TextView>(R.id.stop_alarm_service)


        startAlarmService.setOnClickListener {
            alarmBroadcastReceiver.setAlarm(this)

        }

        stopAlarmService.setOnClickListener {
            alarmBroadcastReceiver.cancelAlarm(this)
        }

    }

}