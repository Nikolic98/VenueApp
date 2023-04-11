package com.example.venueapp.models

/**
 * @author Marko Nikolic on 9.4.23.
 */
data class Token(val value: String, val ttl: Int, val refresh_ttl: Int)