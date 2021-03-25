package com.maagusanalytics.maagus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeScreenTopSellersAdapter : RecyclerView.Adapter<HomeScreenTopSellersAdapter.SellerViewHolder> {

    public var nameList:MutableList<ListingAdapter.ListingData>;
    private var activity:ListingActivity;
    constructor(cxt: ListingActivity, list:MutableList<ListingAdapter.ListingData>) {
        activity = cxt
        nameList = list;
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SellerViewHolder {
        var inflater = LayoutInflater.from(parent.getContext());
        var view = inflater.inflate(R.layout.fragment_home_screen_seller, parent, false);

        view.setOnClickListener{
            val layout = activity.findViewById<ConstraintLayout>(R.id.constraintLayout)
            val callToAction = CallToActionFragment()
            activity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                var fragment = CallToActionFragment()
                activity.callToAction = fragment
                add(R.id.constraintLayout, fragment, "callToAction")
            }
        }

        var myViewHolder = SellerViewHolder(view);
        return myViewHolder;
    }

    override fun getItemCount(): Int {
        return nameList.size;
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        var data = nameList[position]
        holder.nameTextView.text = data.name
        Glide.with(activity)
            .load(data.imageUrl)
            .into(holder.logoImageView)
    }

    public class SellerViewHolder : RecyclerView.ViewHolder {
        public var nameTextView: TextView
        public var logoImageView: ImageView

        constructor(itemView: View) : super(itemView) {

            nameTextView = itemView.findViewById(R.id.sellerNameTextView)
            logoImageView = itemView.findViewById(R.id.sellerLogoImageView)
        }
    }

}