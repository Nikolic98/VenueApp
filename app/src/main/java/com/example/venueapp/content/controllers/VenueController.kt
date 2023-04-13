package com.example.venueapp.content.controllers

import com.example.venueapp.content.response.DefaultAPIResponse
import com.example.venueapp.models.VenuesResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Marko Nikolic on 12.4.23.
 */
interface VenueController {
    @POST("api/directory/search")
    fun venues(@Body body: RequestBody): Call<DefaultAPIResponse<VenuesResponse>>
}