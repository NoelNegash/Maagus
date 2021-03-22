package com.maagusanalytics.maagus

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

private class KillMe(var start:LogoActivity) : Runnable{

    override fun run() {
        var intent = Intent(start, ListingActivity::class.java)
        start.startActivity(intent)
        Thread.sleep(500)
        start.finish();
    }
}


class LogoActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var handler = Handler()
        var runnable = KillMe(this)
        handler.postDelayed(runnable,1000);

        Log.i("I live", "maagus")
    }
}