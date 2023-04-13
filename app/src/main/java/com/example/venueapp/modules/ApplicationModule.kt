package com.example.venueapp.modules

import com.example.venueapp.content.RetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Marko Nikolic on 9.4.23.
 */
@Module
class ApplicationModule {
    companion object {
        //        private val ROOT_URL: String = BuildConfig.ROOT_URL
        private val ROOT_URL: String = "https://api-qa.menu.app/"
    }

    private var retrofitService: RetrofitService? = null

    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        retrofitService = RetrofitService(ROOT_URL)
        return retrofitService!!
    }
}