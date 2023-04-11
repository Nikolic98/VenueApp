package com.example.venueapp.managers

import android.content.Context
import com.example.venueapp.content.AppCallback
import com.example.venueapp.content.RetrofitService
import com.example.venueapp.content.requests.LoginRequestBody
import com.example.venueapp.content.response.AdvancedAPIResponse
import com.example.venueapp.models.LoginResponse
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author Marko Nikolic on 9.4.23.
 */
class LoginManager(private val service: RetrofitService) {
    suspend fun loginUser(context: Context, email: String, password: String): LoginResponse {
        return suspendCoroutine {
            val body = LoginRequestBody(email, password).getRequestBody()
            service.getLoginController().loginUser(
                    body).enqueue(object : AppCallback<AdvancedAPIResponse<LoginResponse>>(
                    context) {
                override fun onSuccess(call: Call<AdvancedAPIResponse<LoginResponse>>,
                        response: Response<AdvancedAPIResponse<LoginResponse>>) {
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