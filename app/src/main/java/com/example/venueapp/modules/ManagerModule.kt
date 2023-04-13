package com.example.venueapp.modules

import android.app.Application
import com.example.venueapp.content.RetrofitService
import com.example.venueapp.managers.LoginManager
import com.example.venueapp.managers.VenueManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Marko Nikolic on 9.4.23.
 */
@Module
class ManagerModule(var application: Application) {

    @Provides
    @Singleton
    fun provideLoginManager(retrofitService: RetrofitService): LoginManager {
        return LoginManager(retrofitService)
    }

    @Provides
    @Singleton
    fun provideVenueManager(retrofitService: RetrofitService): VenueManager {
        return VenueManager(retrofitService)
    }
}