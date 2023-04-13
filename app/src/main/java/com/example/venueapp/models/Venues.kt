package com.example.venueapp.models

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 12.4.23.
 */
data class Venues(@SerializedName("distance") val distance: Float,
        @SerializedName("venue") val venue: VenueModel)
