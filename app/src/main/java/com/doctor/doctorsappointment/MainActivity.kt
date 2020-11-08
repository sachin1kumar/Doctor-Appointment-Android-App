package com.doctor.doctorsappointment

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.doctor.doctorsappointment.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    //lateinit var adviceText: TextView

    companion object {
        var mainActivity: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivity = this

        //adviceText = findViewById(R.id.count)

        supportActionBar?.hide()

        val viewModel = ViewModelProviders.of(this)
            .get(MyViewModel::class.java)

        (viewModel).getTotalReceivedCount().observe(this,
            Observer {
                //adviceText.text = "Total Received Appointments: $it"
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity = null
    }
}