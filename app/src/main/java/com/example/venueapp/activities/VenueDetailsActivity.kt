package com.example.venueapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.venueapp.BoundBaseActivity
import com.example.venueapp.R
import com.example.venueapp.VenueApplication
import com.example.venueapp.databinding.ActivityVenueDetailsBinding
import com.example.venueapp.models.VenueModel
import com.example.venueapp.util.PrefHelper


class VenueDetailsActivity : BoundBaseActivity() {

    private lateinit var binding: ActivityVenueDetailsBinding

    companion object {
        private const val KEY_VENUE = "key:venue"

        fun newInstance(context: Context, venue: VenueModel): Intent {
            val intent = Intent(context, VenueDetailsActivity::class.java)
            intent.putExtra(KEY_VENUE, venue)
            return intent
        }
    }

    override fun injectActivity() {
        VenueApplication[this].getAppComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenueDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: VenueModel = intent.getParcelableExtra(KEY_VENUE)!!
        binding.apply {
            Glide.with(binding.root).load(data.image?.thumbnail).centerCrop().into(binding.image)
            name.text = data.name
            welcome.text = data.welcomeMessage
            description.text = data.description
            workingTime.apply {
                if (data.isOpen) {
                    text = resources.getString(R.string.open)
                    setBackgroundColor(resources.getColor(R.color.button_orange))
                } else {
                    text = resources.getString(R.string.closed)
                    setBackgroundColor(resources.getColor(R.color.button_gray))
                }
            }
            logout.setOnClickListener {
                PrefHelper.clearPrefs()
                startActivity(Intent(this@VenueDetailsActivity, LoginActivity::class.java))
                finish()
            }
            backArrow.setOnClickListener { finish() }
        }
    }
}