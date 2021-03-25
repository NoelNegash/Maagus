package com.maagusanalytics.maagus

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class ListingActivity : AppCompatActivity() {

    var data:MutableList<ListingAdapter.ListingData>? = null
    var callToAction:Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        data = intent.getParcelableArrayListExtra<ListingAdapter.ListingData>("data") as MutableList<ListingAdapter.ListingData>

        var viewPager = findViewById<ViewPager2>(R.id.listingViewPager)
        var pagerAdapter = ListingFragmentStateAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (callToAction != null) {

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                callToAction?.let { remove(it) }
            }
            callToAction = null
        } else {
            super.onBackPressed()
        }
    }
}