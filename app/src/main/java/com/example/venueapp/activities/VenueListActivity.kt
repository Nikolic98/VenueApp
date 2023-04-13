package com.example.venueapp.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.venueapp.BoundBaseActivity
import com.example.venueapp.VenueApplication
import com.example.venueapp.adapter.items.VenueItem
import com.example.venueapp.databinding.ActivityVenueListBinding
import com.example.venueapp.longToast
import com.example.venueapp.models.Venues
import com.example.venueapp.models.VenuesResponse
import com.example.venueapp.viewModel.VenueListViewModel
import com.example.venueapp.viewModel.ViewModelFactory
import com.example.venueapp.viewModel.result.ErrorResultState
import com.example.venueapp.viewModel.result.SuccessResultState
import com.xwray.groupie.GroupieAdapter
import javax.inject.Inject

class VenueListActivity : BoundBaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var venueListViewModel: VenueListViewModel
    private val adapter = GroupieAdapter()

    override fun injectActivity() {
        VenueApplication[this].getAppComponent().inject(this)
    }

    private lateinit var binding: ActivityVenueListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenueListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        venueListViewModel = ViewModelProvider(this,
                viewModelFactory)[VenueListViewModel::class.java.name, VenueListViewModel::class.java]

        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.isRefreshing = true
        initObserver()
        venueListViewModel.getVenues(this)

        binding.swipeRefresh.setOnRefreshListener { venueListViewModel.getVenues(this) }
    }

    private val venueItemListener = object : VenueItem.VenueListener {
        override fun onItemClick(venues: Venues) {
            val intent = VenueDetailsActivity.newInstance(this@VenueListActivity, venues.venue)
            startActivity(intent)
        }
    }

    private fun initObserver() {
        venueListViewModel.venueListResult.observe(this) { result ->
            when (result) {
                is SuccessResultState<*> -> {
                    binding.swipeRefresh.isRefreshing = false
                    adapter.clear()
                    val data = result.result as VenuesResponse
                    if (data.venues.isEmpty()) {
                        binding.emptyHolder.visibility = View.VISIBLE
                    } else {
                        data.venues.forEach {
                            adapter.add(VenueItem(this, it, venueItemListener))
                        }
                    }
                }
                is ErrorResultState -> {
                    binding.swipeRefresh.isRefreshing = false
                    longToast(result.error)
                    if (!venueListViewModel.isConnectedToInternet(this)) {
                        if (adapter.itemCount == 0) {
                            binding.emptyHolder.visibility = View.VISIBLE
                        }
                    } else {
                        binding.emptyHolder.visibility = View.GONE
                    }
                }
            }
        }
    }
}