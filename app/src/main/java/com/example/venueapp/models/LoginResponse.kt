package com.example.venueapp.models

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 9.4.23.
 */
data class LoginResponse(@SerializedName("customer_account") val customerAccount: CustomerAccount,
        @SerializedName("token") val token: Token)