package com.example.venueapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.venueapp.BoundBaseActivity
import com.example.venueapp.R
import com.example.venueapp.VenueApplication
import com.example.venueapp.databinding.ActivityLoginBinding
import com.example.venueapp.longToast
import com.example.venueapp.viewModel.LoginViewModel
import com.example.venueapp.viewModel.ViewModelFactory
import com.example.venueapp.viewModel.result.ErrorResultState
import com.example.venueapp.viewModel.result.SuccessResultState
import javax.inject.Inject

/**
 * @author Marko Nikolic on 9.4.23.
 */
class LoginActivity : BoundBaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var loginViewModel: LoginViewModel

    override fun injectActivity() {
        VenueApplication[this].getAppComponent().inject(this)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this,
                viewModelFactory)[LoginViewModel::class.java.name, LoginViewModel::class.java]

        binding.signInBtn.setOnClickListener { onSignIn() }
        // Dismisses  the keyboard
        binding.root.setOnTouchListener { view, _ ->
            val inputMethodManager = getSystemService(
                    Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        initObserver()
    }

    private fun initObserver() {
        loginViewModel.loginResult.observe(this) { result ->
            when (result) {
                is SuccessResultState<*> -> {
                    stopLoading()
                    startActivity(Intent(this, VenueListActivity::class.java))
                    finish()
                }
                is ErrorResultState -> {
                    stopLoading()
                    longToast(result.error)
                }
            }
        }
    }

    private fun startLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun onSignIn() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (TextUtils.isEmpty(email)) {
            longToast(resources.getText(R.string.email_error))
            return
        }
        if (TextUtils.isEmpty(password)) {
            longToast(resources.getText(R.string.password_error))
            return
        }
        startLoading()

        loginViewModel.loginUser(this@LoginActivity, email, password)
    }
}