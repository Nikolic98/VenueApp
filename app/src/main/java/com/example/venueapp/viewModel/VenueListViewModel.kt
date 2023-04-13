package com.example.venueapp.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venueapp.managers.VenueManager
import com.example.venueapp.viewModel.result.ErrorResultState
import com.example.venueapp.viewModel.result.ResultState
import com.example.venueapp.viewModel.result.SuccessResultState
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Marko Nikolic on 12.4.23.
 */
class VenueListViewModel @Inject constructor(private var venueManager: VenueManager) : ViewModel() {

    val venueListResult: MutableLiveData<ResultState> by lazy { MutableLiveData<ResultState>() }

    fun getVenues(context: Context) {
        viewModelScope.launch {
            try {
                val venues = venueManager.getVenues(context)
                venueListResult.value = SuccessResultState(venues)
            } catch (t: Throwable) {
                venueListResult.value = ErrorResultState(t.localizedMessage)
            }
        }
    }
}