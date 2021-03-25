package com.maagusanalytics.maagus

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListingAdapter : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>{
    public var nameList:MutableList<ListingData>;
    private var activity:ListingActivity;
    constructor(cxt: ListingActivity, list:MutableList<ListingData>) {
        activity = cxt
        nameList = list;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) : ListingViewHolder {
        var inflater = LayoutInflater.from(parent.getContext());
        var view = inflater.inflate(R.layout.fragment_listing, parent, false);

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

        var myViewHolder = ListingViewHolder(view);
        return myViewHolder;
    }
    override fun onBindViewHolder(holder:ListingViewHolder, position:Int) {
        var data = nameList[position]//get(position);
        holder.nameTextView.text = data.name
        holder.priceTextView.text = "Br " + data.price.toString()
        holder.stockTextView.text = data.stock.toString() + " in stock"
        //TODO image view
        Glide.with(activity)
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

    public class ListingData(var name:String, var price:Float, var stock:Int, var marketType:String, var info:String, var imageUrl:String) : Parcelable{

        constructor (i: Parcel) : this("",0f,0,"","","") {
            name = i.readString()?:""
            price = i.readFloat()
            stock = i.readInt()
            marketType = i.readString()?:""
            info = i.readString()?:""
            imageUrl = i.readString()?:""
        }

        companion object CREATOR : Parcelable.Creator<ListingData> {

            override fun createFromParcel(inp: Parcel): ListingData? {
                return ListingData(inp)
            }

            override fun newArray(size: Int): Array<ListingData?> {
                return arrayOfNulls<ListingData>(size)
            }
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            out.writeString(name)
            out.writeFloat(price)
            out.writeInt(stock)
            out.writeString(marketType)
            out.writeString(info)
            out.writeString(imageUrl)
        }





    }
}