package com.example.venueapp.modules

import androidx.lifecycle.ViewModel
import com.example.venueapp.viewModel.LoginViewModel
import com.example.venueapp.viewModel.VenueListViewModel
import com.example.venueapp.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Marko Nikolic on 9.4.23.
 */

@Module
abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VenueListViewModel::class)
    abstract fun provideVenueListViewModel(viewModel: VenueListViewModel): ViewModel
}