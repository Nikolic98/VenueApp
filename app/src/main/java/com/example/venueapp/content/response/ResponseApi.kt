package com.example.venueapp.content.response

import com.google.gson.annotations.SerializedName

/**
 * @author Marko Nikolic on 9.4.23.
 */
open class BaseAPIResponse {
    @SerializedName("status")
    val status: String = ""
}

open class DefaultAPIResponse<T> : BaseAPIResponse() {
    @SerializedName("data")
    val data: T? = null
}

open class AdvancedAPIResponse<T> : DefaultAPIResponse<T>() {
    @SerializedName("info_message")
    val errors: List<ResponseError>? = null
}

class ResponseError(@SerializedName("title") val title: String,
        @SerializedName("body") val body: String)
