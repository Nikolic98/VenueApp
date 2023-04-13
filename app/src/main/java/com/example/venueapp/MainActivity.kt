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
}