package com.example.venueapp.adapter.items

import android.view.View
import com.example.venueapp.R
import com.example.venueapp.databinding.VenueItemBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * @author Marko Nikolic on 11.4.23.
 */
class VenueItem : BindableItem<VenueItemBinding>() {
    private lateinit var listener: VenueListener

    override fun getLayout() = R.layout.venue_item

    override fun initializeViewBinding(view: View): VenueItemBinding {
        return VenueItemBinding.bind(view)
    }

    override fun bind(viewBinding: VenueItemBinding, position: Int) {
    }

    fun setListener(listener: VenueListener) {
        this.listener = listener
    }
}

interface VenueListener {
    fun itemClick()
}