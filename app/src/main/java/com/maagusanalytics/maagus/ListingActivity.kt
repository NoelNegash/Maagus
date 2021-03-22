package com.maagusanalytics.maagus

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley


class ListingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)



        var recyclerView:RecyclerView = findViewById(R.id.listingRecyclerView)
        recyclerView.setHasFixedSize(true);
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.getOrientation()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)


        val queue = Volley.newRequestQueue(this)
        val url = "https://maagusanalytics.com/wp-json/wc/v3/products?consumer_key=ck_e24aa1cb3d43abb310fc9d1d183b059090a42865&consumer_secret=cs_3fd7bd31bf9f113024ceb99824eb309e44fcc1da"

        // Request a string response from the provided URL.
        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                var data = mutableListOf<ListingAdapter.ListingData>()
                for (i in 0 until response.length()) {

                    var item = response.getJSONObject(i)

                    var name = item.get("name") as String
                    var price = (item.get("price") as String).toFloat()
                    var stock = item.get("stock_quantity") as Int
                    var marketType = ""
                    var imageUrl = ""

                    var metadata = item.getJSONArray("meta_data")
                    for (j in 0 until metadata.length()) {
                        if (metadata.getJSONObject(j).get("key") == "market_type") {
                            marketType = metadata.getJSONObject(j).get("value") as String
                            break
                        }
                    }
                    var images = item.getJSONArray("images")
                    imageUrl = images.getJSONObject(0).get("src") as String

                    var datum = ListingAdapter.ListingData(name, price, stock, marketType, "", imageUrl)
                    data.add(datum)

                }

                var myAdapter = ListingAdapter(this, data);
                recyclerView.adapter = myAdapter

            },
            Response.ErrorListener { })

        // Add the request to the RequestQueue.
        queue.add(jsonRequest)
    }
}