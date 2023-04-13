package com.example.venueapp.models

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 13.4.23.
 */
data class VenuesResponse(@SerializedName("venues") val venues: List<Venues>)