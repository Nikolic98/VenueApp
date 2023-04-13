package com.example.venueapp.content.requests

import com.example.venueapp.content.GsonProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


/**
 * @author Marko Nikolic on 9.4.23.
 */
open class ApiPostData() {

    fun getRequestBody(): RequestBody {
        val gson = GsonProvider.instance.nullNotSerialized
        val body = gson.toJson(this)
        return RequestBody.create("application/json".toMediaTypeOrNull(), body)
    }
}

data class LoginRequestBody(val email: String, val password: String) : ApiPostData()
data class VenueRequestBody(val latitude: String, val longitude: String) : ApiPostData()