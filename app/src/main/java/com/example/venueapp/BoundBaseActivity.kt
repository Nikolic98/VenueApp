package com.example.venueapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Marko Nikolic on 9.4.23.
 */
abstract class BoundBaseActivity : AppCompatActivity() {

    /**
     * Called from the [.onCreate] method of the Activity that extends the BoundBaseActivity.
     */
    protected abstract fun injectActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            VenueApplication[this].getAppComponent().inject(this)
        }
        injectActivity()
    }

//    fun setupActionToolbar(toolbar: Toolbar) {
//        toolbar.title = ""
//        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
//        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//    }
}