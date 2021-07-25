package com.newagedevs.androidhomescreenwidget

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class PrefManager(context: Context?) {

    private var pref: SharedPreferences
    private var editor: Editor
    private var privateMode = 0

    private val prefName = "home-screen-widget"
    private val manualUpdate = "manualUpdate"
    private val autoUpdate = "autoUpdate"
    private val lastUpdate = "lastUpdate"
    private val currentUpdate = "currentUpdate"

    init {
        pref = context!!.getSharedPreferences(prefName, privateMode)
        editor = pref.edit()
    }


    fun setManualUpdate() {
        var value = getManualUpdate()
        value++
        editor.putInt(manualUpdate, value)
        editor.commit()
    }

    fun getManualUpdate():Int {
        return pref.getInt(manualUpdate, 0)
    }


    fun setAutoUpdate() {
        var value = getAutoUpdate()
        value++
        editor.putInt(autoUpdate, value)
        editor.commit()
    }

    fun getAutoUpdate(): Int {
        return pref.getInt(autoUpdate, 0)
    }



    fun setLastUpdate(value: String) {
        editor.putString(lastUpdate, value)
        editor.commit()
    }

    fun getLastUpdate(): String {
        return pref.getString(lastUpdate, "---").toString()
    }

    fun setCurrentUpdate(value: String) {
        editor.putString(currentUpdate, value)
        editor.commit()
    }

    fun getCurrentUpdate(): String {
        return pref.getString(currentUpdate, "---").toString()
    }

}