package com.maagusanalytics.maagus

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListingSpacingItemDecoration (var spacing:Int = 30) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = spacing
        outRect.bottom = spacing
        outRect.left = spacing
        outRect.right = spacing
    }
}