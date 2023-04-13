package com.example.venueapp.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venueapp.R
import com.example.venueapp.managers.LoginManager
import com.example.venueapp.network.NetworkConnectionResolve
import com.example.venueapp.util.PrefHelper
import com.example.venueapp.viewModel.result.ErrorResultState
import com.example.venueapp.viewModel.result.ResultState
import com.example.venueapp.viewModel.result.SuccessResultState
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Marko Nikolic on 9.4.23.
 */
class LoginViewModel @Inject constructor(private var loginManager: LoginManager) : ViewModel() {

    val loginResult: MutableLiveData<ResultState> by lazy { MutableLiveData<ResultState>() }

    fun loginUser(context: Context, email: String, password: String) {
        viewModelScope.launch {
            if (NetworkConnectionResolve.determineConnection(context)) {
                try {
                    val login = loginManager.loginUser(context, email, password)
                    PrefHelper.saveToken(login.token.value)
                    loginResult.value = SuccessResultState(Unit)
                } catch (t: Throwable) {
                    loginResult.value = ErrorResultState(t.localizedMessage!!)
                }
            } else {
                loginResult.value = ErrorResultState(
                        context.resources.getString(R.string.no_internet))
            }
        }
    }
}