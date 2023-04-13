package com.example.venueapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.venueapp.activities.LoginActivity
import com.example.venueapp.activities.VenueListActivity
import com.example.venueapp.databinding.ActivityMainBinding
import com.example.venueapp.util.PrefHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (PrefHelper.isTokenSaved) {
            startActivity(Intent(this, VenueListActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

    private fun crashTest() {
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT))
    }
}