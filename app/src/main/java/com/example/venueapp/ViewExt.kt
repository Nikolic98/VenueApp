package com.example.venueapp

import android.content.Context
import android.widget.Toast

/**
 * @author Marko Nikolic on 11.4.23.
 */
fun Context.longToast(message: CharSequence): Toast = Toast.makeText(this, message,
            Toast.LENGTH_LONG).apply {
        show()
    }