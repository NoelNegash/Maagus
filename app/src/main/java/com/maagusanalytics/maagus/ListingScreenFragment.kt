package com.maagusanalytics.maagus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListingScreenFragment (var activity:ListingActivity): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_listing_screen, container, false)

        var recyclerView: RecyclerView = view.findViewById(R.id.listingRecyclerView)
        recyclerView.setHasFixedSize(true);

        var layoutManager = GridLayoutManager(activity, 2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ListingAdapter(activity, activity.data?: mutableListOf())

        var spacing = ListingSpacingItemDecoration(7)
        recyclerView.addItemDecoration(spacing)
        return view
    }

}