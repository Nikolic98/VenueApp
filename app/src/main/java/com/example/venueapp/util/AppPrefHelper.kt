package com.example.venueapp.util

import android.content.SharedPreferences

/**
 * @author Marko Nikolic on 13.4.23.
 */
open class AppPrefHelper {

    companion object {
        const val DEFAULT_STRING = ""

        @JvmStatic
        fun saveStringValue(prefs: SharedPreferences, key: String, value: String) {
            val editor = prefs.edit()
            editor.putString(key, value)
            editor.commit()
        }

        @JvmStatic
        fun getStringValue(prefs: SharedPreferences, key: String): String {
            return prefs.getString(key, DEFAULT_STRING) ?: DEFAULT_STRING
        }
    }
}