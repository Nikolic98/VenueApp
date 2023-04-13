package com.example.venueapp.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Marko Nikolic on 13.4.23.
 */
object NetworkConnectionResolve {
    fun determineConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}