package com.doctor.doctorsappointment.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PreferenceManager {

    companion object {

        private const val DOCTOR_SHARED_PREF = "doctor_preference"

        fun walkThroughCompleted(isCompleted: Boolean) {
            val settings: SharedPreferences = MyApplication.instance.getSharedPreferences(DOCTOR_SHARED_PREF, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = settings.edit()
            editor.putBoolean(Constants.IS_WALK_THROUGH_DONE, isCompleted)
            editor.apply()
        }

        fun isWalkThroughCompleted() : Boolean {
            return MyApplication.instance.getSharedPreferences(DOCTOR_SHARED_PREF, MODE_PRIVATE).getBoolean(Constants.IS_WALK_THROUGH_DONE, false)
        }

        fun loggedInSuccessfully(isLoggedIn: Boolean) {
            val settings: SharedPreferences = MyApplication.instance.getSharedPreferences(DOCTOR_SHARED_PREF, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = settings.edit()
            editor.putBoolean(Constants.IS_LOGGED_IN, isLoggedIn)
            editor.apply()
        }

        fun isLoggedIn() : Boolean {
            return MyApplication.instance.getSharedPreferences(DOCTOR_SHARED_PREF, MODE_PRIVATE).getBoolean(Constants.IS_LOGGED_IN, false)
        }
    }
}