package com.doctor.doctorsappointment.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.doctor.doctorsappointment.R
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.pogress_bar.view.*

class CustomProgress : DialogFragment() {

    private var progress : AVLoadingIndicatorView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pogress_bar, container, false)
        progress = view.progress
        return view
    }


}