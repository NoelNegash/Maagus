package com.maagusanalytics.maagus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment


class CallToActionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_call_to_action, container, false)


        view.findViewById<View>(R.id.bubbleView).setOnTouchListener { v, event -> true }

        view.findViewById<ImageButton>(R.id.telegramButton).setOnClickListener {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/ViepE2BQD0E4YTg0"))
            startActivity(telegram)
        }

        view.findViewById<ImageButton>(R.id.phoneButton).setOnClickListener {
            val uri = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(uri)
            startActivity(intent)

        }

        view.findViewById<ImageButton>(R.id.mailButton).setOnClickListener {

            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("mymoney@hoe.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Where's My Money, Hoe?")
            email.putExtra(Intent.EXTRA_TEXT, "Where's My Money, Hoe?")

            email.type = "message/rfc822"

            startActivity(Intent.createChooser(email, "Choose an Email client :"))
        }

        return view
    }

}