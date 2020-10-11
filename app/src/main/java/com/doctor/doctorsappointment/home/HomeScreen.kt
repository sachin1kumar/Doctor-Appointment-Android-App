package com.doctor.doctorsappointment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.utils.Constants
import com.doctor.doctorsappointment.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeScreen : AppCompatActivity() {

    lateinit var doctorId: String

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

    private fun getDoctorIdFromPref() {
        val doctorId = PreferenceManager.getDoctorId()
        Log.e("Bundle", "adocId:$doctorId")
    }
}