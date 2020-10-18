package com.doctor.doctorsappointment.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.doctor.doctorsappointment.R

class CustomProgress(activity: Activity) {

    private var activity: Activity? = activity
    private var alertDialog: AlertDialog? = null

    fun startLoadingDialog() {
        val builder = activity?.let { AlertDialog.Builder(it) }
        val inflater = activity?.layoutInflater
        builder?.setView(inflater?.inflate(R.layout.pogress_bar, null))
        builder?.setCancelable(false)

        alertDialog = builder?.create()
        alertDialog?.show()
    }

    fun dismissDialog() {
        alertDialog?.cancel()
    }


}