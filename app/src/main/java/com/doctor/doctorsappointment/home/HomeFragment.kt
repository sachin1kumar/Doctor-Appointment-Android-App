package com.doctor.doctorsappointment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.utils.PreferenceManager

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        getDoctorIdFromPref()
        return view
    }

    private fun getDoctorIdFromPref() {
        val doctorId = PreferenceManager.getDoctorId()
        Log.e("Bundle", "fdocId:$doctorId")
    }

}