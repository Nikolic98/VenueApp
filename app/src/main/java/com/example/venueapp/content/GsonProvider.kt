package com.example.venueapp.content

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author Marko Nikolic on 9.4.23.
 */
class GsonProvider private constructor() {
    private var gsonBuilder: GsonBuilder? = null
        get() {
            if (field == null) {
                field = GsonBuilder()
            }
            return field
        }
    private var gsonNullBuilder: GsonBuilder? = null
        get() {
            if (field == null) {
                field = GsonBuilder()
            }
            return field
        }

    private lateinit var gson: Gson
    val nullSerialized: Gson
        get() = getGson(true)
    val nullNotSerialized: Gson
        get() = getGson(false)

    private fun getGson(serializeNulls: Boolean): Gson {
        gson = if (serializeNulls) {
            gsonNullBuilder!!.serializeNulls().create()
        } else {
            gsonBuilder!!.create()
        }
        return gson
    }

    companion object {
        private var GsonProvider: GsonProvider? = null
        val instance: GsonProvider
            get() {
                if (GsonProvider == null) {
                    GsonProvider = GsonProvider()
                }
                return GsonProvider!!
            }
    }
}