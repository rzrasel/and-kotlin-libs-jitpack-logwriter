package com.rzrasel.logwriter

import android.content.Context
import android.content.pm.ApplicationInfo

object BuildConfig {

    private var sIsDebuggable: Boolean? = null

    fun isDebuggable(context: Context): Boolean {
        return sIsDebuggable ?: run {
            val debuggable = (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
            sIsDebuggable = debuggable
            debuggable
        }
    }
}