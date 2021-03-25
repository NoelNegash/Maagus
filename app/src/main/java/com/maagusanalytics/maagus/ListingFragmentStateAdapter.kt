package com.maagusanalytics.maagus

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

public class ListingFragmentStateAdapter(var activity: ListingActivity) : FragmentStateAdapter(activity) {

    private val NUM_ITEMS = 2

    override fun createFragment(i: Int): Fragment {
        if (i == 0)
            return HomeScreenFragment(activity)
        else {

            Log.i("maagus", "LISTING SCREEN")
            return ListingScreenFragment(activity)
        }
    }

    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

}