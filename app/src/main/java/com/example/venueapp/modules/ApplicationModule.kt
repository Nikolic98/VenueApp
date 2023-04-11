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
    private var retrofitService: RetrofitService? = null

//    @Provides
//    @Singleton
//    fun provideCrashLyticsHelper(): CrashLyticsHelper {
//        return CrashLyticsHelper(application.applicationContext)
//    }

    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        retrofitService = RetrofitService(ROOT_URL)
        return retrofitService!!
    }
//
//    @Provides
//    @Singleton
//    fun provideApplicationRouter(): ApplicationRouter {
//        return ApplicationRouter()
//    }

    fun getRetrofitService(): RetrofitService? {
        return retrofitService
    }

    companion object {
        //        private val ROOT_URL: String = BuildConfig.ROOT_URL
        private val ROOT_URL: String = "https://api-qa.menu.app/"
    }
}