package com.example.venueapp.managers

import android.content.Context
import com.example.venueapp.content.AppCallback
import com.example.venueapp.content.RetrofitService
import com.example.venueapp.content.requests.VenueRequestBody
import com.example.venueapp.content.response.DefaultAPIResponse
import com.example.venueapp.models.VenuesResponse
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author Marko Nikolic on 12.4.23.
 */

class VenueManager(private val service: RetrofitService) {

    suspend fun getVenues(context: Context): VenuesResponse {
        return suspendCoroutine {
            val body = VenueRequestBody("44.001783", "21.26907").getRequestBody()
            service.getVenueController().venues(
                    body).enqueue(object : AppCallback<DefaultAPIResponse<VenuesResponse>>(
                    context) {
                override fun onSuccess(call: Call<DefaultAPIResponse<VenuesResponse>>,
                        response: Response<DefaultAPIResponse<VenuesResponse>>) {
                    if (response.body() != null) {
                        it.resume(response.body()!!.data!!)
                    }
                }

                override fun onError(error: String) {
                    it.resumeWithException(Throwable(error))
                }
            })
        }
    }
}