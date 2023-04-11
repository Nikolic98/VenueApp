package com.example.venueapp.modules

import androidx.lifecycle.ViewModelProvider
import com.example.venueapp.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @author Marko Nikolic on 9.4.23.
 */

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}