package com.example.venueapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.venueapp.modules.AppComponent
import com.example.venueapp.modules.ApplicationModule
import com.example.venueapp.modules.DaggerAppComponent
import com.example.venueapp.modules.ManagerModule
import com.example.venueapp.util.PrefHelper

/**
 * @author Marko Nikolic on 9.4.23.
 */
class VenueApplication : Application(), LifecycleObserver {

    companion object {
        @JvmStatic
        operator fun get(context: Context): VenueApplication {
            return context.applicationContext as VenueApplication
        }

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    private lateinit var appComponent: AppComponent
    private lateinit var applicationModule: ApplicationModule
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        PrefHelper.initPrefs(applicationContext, getString(R.string.shared_prefs), MODE_PRIVATE)
    }

    private fun initAppComponent() {
        applicationModule = ApplicationModule()
        Log.i(VenueApplication::class.java.name, "Init()")
        appComponent = DaggerAppComponent.builder().applicationModule(
                applicationModule).managerModule(ManagerModule(this)).build()
    }

    fun getAppComponent(): AppComponent {
        if (!::appComponent.isInitialized) {
            initAppComponent()
        }
        return appComponent
    }
}