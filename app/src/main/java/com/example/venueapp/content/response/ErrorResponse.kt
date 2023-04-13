package com.example.venueapp.content.response

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 13.4.23.
 */
data class ErrorResponse(@SerializedName("info_message") val error: ResponseError)