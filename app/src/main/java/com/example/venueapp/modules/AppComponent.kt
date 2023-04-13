package com.example.venueapp.modules

import com.example.venueapp.BoundBaseActivity
import com.example.venueapp.activities.LoginActivity
import com.example.venueapp.activities.VenueDetailsActivity
import com.example.venueapp.activities.VenueListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * @author Marko Nikolic on 9.4.23.
 */
@Singleton
@Component(
        modules = [ApplicationModule::class, ManagerModule::class, ViewModelFactoryModule::class, ViewModelModules::class])
interface AppComponent {

    fun inject(boundBaseActivity: BoundBaseActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(venueListActivity: VenueListActivity)
    fun inject(venueDetailsActivity: VenueDetailsActivity)
}