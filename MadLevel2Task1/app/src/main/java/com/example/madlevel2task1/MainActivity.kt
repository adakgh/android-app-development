package com.example.madlevel2task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel2task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Wiring the adapter to the RecyclerView.
    private val places = arrayListOf<Place>()
    private val placeAdapter = PlaceAdapter(places)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        // Set the layout manager (StaggeredGridLayoutManager) and adapter of the RecyclerView.
        binding.rvPlaces.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvPlaces.adapter = placeAdapter

        // Populate the places list
        for (i in Place.PLACE_NAMES.indices) {
            places.add(Place(Place.PLACE_NAMES[i],
                Place.PLACE_RES_DRAWABLE_IDS[i]))
        }

        // Notify the data set has changed.
        placeAdapter.notifyDataSetChanged()
    }


}