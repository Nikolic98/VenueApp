package com.example.venueapp.content

import androidx.annotation.NonNull
import com.example.venueapp.content.controllers.LoginController
import com.example.venueapp.content.controllers.VenueController
import com.example.venueapp.content.requests.RequestConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Marko Nikolic on 9.4.23.
 */
class RetrofitService(rootUrl: String) {
    private var loginController: LoginController? = null
    private var venueController: VenueController? = null

    init {
        if (ROOT_URL.isNullOrEmpty()) {
            ROOT_URL = rootUrl
        }
        val gsonBuilder = GsonBuilder()
        val gson: Gson = gsonBuilder.create()
        builder = Retrofit.Builder().baseUrl(ROOT_URL!!).addConverterFactory(
                GsonConverterFactory.create(gson))
    }

    fun setupLoginController(token: String) {
        RetrofitService.token = token
        loginController = RetrofitService.createService(LoginController::class.java)
    }

    fun resetTokenAndControllers() {
        RetrofitService.token = null
        loginController = null
    }

    fun setupAllControllers(token: String) {
        RetrofitService.Companion.token = token
        loginController = RetrofitService.createService(LoginController::class.java)
    }

    fun getLoginController(): LoginController {
        if (loginController == null) {
            loginController = RetrofitService.createService(LoginController::class.java)
        }
        return loginController!!
    }

    fun getVenueController(): VenueController {
        if (venueController == null) {
            venueController = RetrofitService.createService(VenueController::class.java)
        }
        return venueController!!
    }

    val secondsTimeout: Long
        get() = RetrofitService.Companion.SECONDS_TIMEOUT

    /**
     * Service controller paired with [OkHttpClient] which will be used with that controller.
     * This class is the wrapper to enable easier call cancelling.
     *
     * @param <T> Service controller type.
    </T> */
    class ControllerControl<T> internal constructor(@get:NonNull @param:NonNull val controller: T,
            @get:NonNull @param:NonNull val okHttpClient: OkHttpClient)

    companion object {
        private const val SECONDS_TIMEOUT: Long = 35
        private lateinit var builder: Retrofit.Builder
        private var humanityOkHttpClient: OkHttpClient? = null
        private var ROOT_URL: String? = null
        const val API_SUFFIX = "api/"
        const val REFRESH_GRANT_TYPE = "refresh_token"
        private var token: String? = null

        /**
         * Service instantiating class per controller.
         * This method will also concatenate the required access token to the request via
         * the usage of interceptor.
         *
         * @param serviceClass Controller class, endpoint specific
         * @param <S>
         * @return The created content controller.
        </S> */
        private fun <S> createService(serviceClass: Class<S>): S {
            val interceptor: Interceptor = getTokenInterceptor()
            val httpClient: OkHttpClient = RetrofitService.createHttpClient(interceptor)
            val retrofit: Retrofit = builder.client(httpClient).baseUrl(ROOT_URL).build()
            return retrofit.create(serviceClass)
        }

        private fun getTokenInterceptor(): Interceptor {
            return Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val header = request.header(RequestConstants.AUTHORIZATION)
                if (header != null) {
                    return@Interceptor chain.proceed(request)
                }
                if (token == null) {
                    // todo
//                    token = PrefHelper.getString(CoreValues.ACCOUNT_TOKEN)
                }
                val requestBuilder = request.newBuilder().header(RequestConstants.APPLICATION,
                            "mobile-application").header("Content-Type", "application/json").header(
                            "Device-UUID", "123456").header("Api-Version", "3.7.0").header(
                            RequestConstants.AUTHORIZATION,
                            RequestConstants.BEARER + " " + token).method(request.method,
                            request.body)
                val generatedRequest = requestBuilder.build()
                chain.proceed(generatedRequest)
            }
        }

        /**
         * [OkHttpClient] initialization. Timeouts and interceptor are set here.
         *
         * @param interceptor [Interceptor] to be added to the client.
         * @return Initialized [OkHttpClient].
         */
        private fun createHttpClient(interceptor: Interceptor): OkHttpClient {
            val builder: OkHttpClient.Builder = getBaseOkHttpClient().newBuilder()
            return RetrofitService.createClient(builder, interceptor)
        }

        private fun getBaseOkHttpClient(): OkHttpClient {
            if (humanityOkHttpClient == null) {
                humanityOkHttpClient = OkHttpClient()
            }
            return humanityOkHttpClient as OkHttpClient
        }

        private fun createClient(builder: OkHttpClient.Builder,
                interceptor: Interceptor?): OkHttpClient {
            builder.connectTimeout(RetrofitService.SECONDS_TIMEOUT, TimeUnit.SECONDS).readTimeout(
                    RetrofitService.SECONDS_TIMEOUT, TimeUnit.SECONDS).writeTimeout(
                    RetrofitService.SECONDS_TIMEOUT, TimeUnit.SECONDS)
            builder.addInterceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request()
                val response: Response = chain.proceed(request)
                if (response.code === 307 && !request.method.equals("GET")) {
                    val requestBuilder = request.newBuilder().header(RequestConstants.APPLICATION,
                                "mobile-application").header("Content-Type",
                                "application/json").header("Device-UUID", "123456").header(
                                "Api-Version", "3.7.0")
                    val generatedRequest: Request = requestBuilder.build()
                    return@addInterceptor chain.proceed(generatedRequest)
                }
                response
            }
            if (interceptor != null) {
                builder.addInterceptor(interceptor)
            }
            builder.followRedirects(true)
            builder.followSslRedirects(true)
            return builder.build()
        }


        val timeoutMillis: Long
            get() = TimeUnit.SECONDS.toMillis(RetrofitService.Companion.SECONDS_TIMEOUT)

    }
}