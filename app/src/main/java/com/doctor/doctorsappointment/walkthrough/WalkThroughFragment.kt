package com.doctor.doctorsappointment.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.walkthrough.screens.FirstScreen
import com.doctor.doctorsappointment.walkthrough.screens.SecondScreen
import com.doctor.doctorsappointment.walkthrough.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class WalkThroughFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = WalkThroughAdapter(fragmentList, requireActivity().supportFragmentManager,
        lifecycle)

        view.viewPager.adapter = adapter
        
        return view
    }
}