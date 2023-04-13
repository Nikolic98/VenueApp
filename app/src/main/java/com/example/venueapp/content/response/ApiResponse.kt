package com.example.venueapp.content.response

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 10.4.23.
 */
data class ApiResponse(@SerializedName("data") var data: ErrorResponse)