package com.doctor.doctorsappointment.walkthrough

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.home.HomeScreenActivity
import com.doctor.doctorsappointment.utils.PreferenceManager

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!PreferenceManager.isLoggedIn() && PreferenceManager.isWalkThroughCompleted()) {
            Handler().postDelayed({
                findNavController().navigate(R.id.doctorRegistrationFragment)
            }, 5000)
        } else if (PreferenceManager.isLoggedIn()) {
            Handler().postDelayed({
                //launch home screen.
                val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
                startActivity(intent)
            }, 5000)
        } else {
            Handler().postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }, 5000)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}