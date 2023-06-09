package com.example.venueapp.content

import android.content.Context
import android.text.TextUtils
import com.example.venueapp.content.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * @author Marko Nikolic on 10.4.23.
 */
abstract class AppCallback<T : BaseAPIResponse>(private val context: Context) : Callback<T> {

    private val unknownErrorMessage = "Could not get response"

    override fun onResponse(call: Call<T>, response: Response<T>) {
        Thread {
            if (response.isSuccessful) {
                onSuccess(call, response)
                return@Thread
            } else {
                parseError(response)
                return@Thread
            }
        }.start()
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Thread(Runnable {
            if (TextUtils.isEmpty(t.message)) {
                onError("Server sent invalid response")
                return@Runnable
            }
        }).start()
    }

    private fun parseError(response: Response<T>) {
        val errorBody = response.errorBody()
        val gson = GsonProvider.instance.nullSerialized
        try {
            val apiResponse = gson.fromJson(errorBody!!.string(), ApiResponse::class.java)
            val errorMessage = apiResponse.data.error.body
            onError(errorMessage)
        } catch (e: IOException) {
            sendFailure(RuntimeException(unknownErrorMessage))
        } catch (e2: Exception) {
            sendFailure(RuntimeException(unknownErrorMessage))
        }
    }

    private fun sendFailure(e: Exception) {
        onError(e.message ?: unknownErrorMessage)
    }

    protected abstract fun onSuccess(call: Call<T>, response: Response<T>)

    protected abstract fun onError(error: String)
}