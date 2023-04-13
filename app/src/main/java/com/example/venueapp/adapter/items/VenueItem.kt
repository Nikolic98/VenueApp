package com.example.venueapp.adapter.items

import android.content.Context
import android.view.View
import com.example.venueapp.R
import com.example.venueapp.databinding.VenueItemBinding
import com.example.venueapp.models.Venues
import com.xwray.groupie.viewbinding.BindableItem

/**
 * @author Marko Nikolic on 11.4.23.
 */
class VenueItem(val context: Context, private val venues: Venues,
        private val listener: VenueListener) : BindableItem<VenueItemBinding>() {

    override fun getLayout() = R.layout.venue_item

    override fun initializeViewBinding(view: View): VenueItemBinding {
        return VenueItemBinding.bind(view)
    }

    override fun bind(viewBinding: VenueItemBinding, position: Int) {
        viewBinding.apply {
            root.setOnClickListener { listener.onItemClick(venues) }
            name.text = venues.venue.name
            address.text = venues.venue.address
            distnace.text = venues.distance.toString() + " m"
            if (venues.venue.isOpen) {
                name.setTextColor(context.resources.getColor(R.color.black))
                distnace.setTextColor(context.resources.getColor(R.color.black))
                address.setTextColor(context.resources.getColor(R.color.grey2))
                workingTime.setTextColor(context.resources.getColor(R.color.grey2))
                workingTime.text = context.resources.getString(R.string.open)
            } else {
                name.setTextColor(context.resources.getColor(R.color.unactive_grey))
                distnace.setTextColor(context.resources.getColor(R.color.unactive_grey))
                address.setTextColor(context.resources.getColor(R.color.unactive_grey))
                workingTime.setTextColor(context.resources.getColor(R.color.unactive_grey))
                workingTime.text = context.resources.getString(R.string.closed)
            }
        }
    }

    interface VenueListener {
        fun onItemClick(venues: Venues)
    }
}