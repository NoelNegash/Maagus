package com.maagusanalytics.maagus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreenFragment (var activity: ListingActivity): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        var recyclerView: RecyclerView = view.findViewById(R.id.topSellersRecyclerView)
        recyclerView.setHasFixedSize(true);

        var layoutManager = GridLayoutManager(activity, 2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = HomeScreenTopSellersAdapter(activity,
            activity.data?.slice(0..3) as MutableList<ListingAdapter.ListingData>)

        var spacing = ListingSpacingItemDecoration(10)
        recyclerView.addItemDecoration(spacing)
        return view
    }

}