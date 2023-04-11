package com.example.venueapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.venueapp.adapter.items.VenueItem
import com.example.venueapp.databinding.ActivityVenueListBinding
import com.xwray.groupie.GroupieAdapter

class VenueListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVenueListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenueListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GroupieAdapter()
        for (i in 0..10) {
            adapter.add(VenueItem())
        }
        binding.recyclerView.adapter = adapter
    }
}