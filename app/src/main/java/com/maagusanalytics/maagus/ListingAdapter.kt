package com.maagusanalytics.maagus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListingAdapter : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>{
    private var nameList:MutableList<ListingData>;
    private var context:Context;
    constructor(cxt: Context, list:MutableList<ListingData>) {
        context = cxt
        nameList = list;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) : ListingViewHolder {
        var inflater = LayoutInflater.from(parent.getContext());
        var view = inflater.inflate(R.layout.fragment_listing, parent, false);
        var myViewHolder = ListingViewHolder(view);
        return myViewHolder;
    }
    override fun onBindViewHolder(holder:ListingViewHolder, position:Int) {
        var data = nameList[position]//get(position);
        holder.nameTextView.text = data.name
        holder.priceTextView.text = "Br " + data.price.toString()
        holder.stockTextView.text = data.stock.toString() + " in stock"
        //TODO image view
        Glide.with(context)
            .load(data.imageUrl)
            .into(holder.logoImageView)
        //holder.textView.setText(name);
    }
    override fun getItemCount() : Int {
        if (nameList==null) {
            return 0;
        } else {
            return nameList.size;
        }
    }
    public class ListingViewHolder : RecyclerView.ViewHolder {
        public var nameTextView:TextView
        public var priceTextView:TextView
        public var stockTextView:TextView
        public var logoImageView:ImageView

        constructor(itemView:View) : super(itemView) {

            nameTextView = itemView.findViewById(R.id.nameTextView)
            priceTextView = itemView.findViewById(R.id.priceTextView)
            stockTextView = itemView.findViewById(R.id.stockTextView)
            logoImageView = itemView.findViewById(R.id.logoImageView)
        }
    }

    public class ListingData(var name:String, var price:Float, var stock:Int, var marketType:String, var info:String, var imageUrl:String) {}
}