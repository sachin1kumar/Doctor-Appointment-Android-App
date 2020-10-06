package com.doctor.doctorsappointment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.doctor.doctorsappointment.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        onClickListener()
    }

    private fun onClickListener() {
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}