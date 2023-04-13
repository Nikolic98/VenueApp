package com.example.venueapp.content.controllers

import com.example.venueapp.content.response.DefaultAPIResponse
import com.example.venueapp.models.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Marko Nikolic on 9.4.23.
 */
interface LoginController {
    @POST("api/customers/login")
    fun loginUser(@Body body: RequestBody): Call<DefaultAPIResponse<LoginResponse>>
}