package com.maagusanalytics.maagus

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley



class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        var startTime = System.currentTimeMillis()

        val queue = Volley.newRequestQueue(this)
        val url = "https://maagusanalytics.com/wp-json/wc/v3/products?consumer_key=ck_e24aa1cb3d43abb310fc9d1d183b059090a42865&consumer_secret=cs_3fd7bd31bf9f113024ceb99824eb309e44fcc1da"

        // Request a string response from the provided URL.
        val jsonRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
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

                var intent = Intent(this, ListingActivity::class.java)
                intent.putExtra("data", data as ArrayList<ListingAdapter.ListingData>)

                if (System.currentTimeMillis()-startTime < 3000) {
                    Thread.sleep(3000-(System.currentTimeMillis()-startTime))
                }
                startActivity(intent)

                Thread.sleep(500)
                finish();

            },
            Response.ErrorListener { Toast.makeText(this, "Network Failed!", Toast.LENGTH_SHORT)})

        // Add the request to the RequestQueue.
        queue.add(jsonRequest)

    }
}