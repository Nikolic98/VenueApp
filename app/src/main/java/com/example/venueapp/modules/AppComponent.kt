package com.example.venueapp.modules

import com.example.venueapp.BoundBaseActivity
import com.example.venueapp.activities.LoginActivity
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
}