package com.doctor.doctorsappointment

import android.app.Instrumentation
import androidx.fragment.app.Fragment
import androidx.test.InstrumentationRegistry
import com.doctor.doctorsappointment.MyTestRunner.startFragment
import com.doctor.doctorsappointment.doctorregistration.view.DoctorRegistrationFragment
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class DoctorRegistrationFragTest {

    private var mainActivity: MainActivity? = null
    private var doctorRegistrationFragment: DoctorRegistrationFragment? = null

    @Before
    @Throws(java.lang.Exception::class)
    fun setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        doctorRegistrationFragment = DoctorRegistrationFragment.newInstance()
        startFragment(doctorRegistrationFragment!!, DoctorRegistrationFragment::class.simpleName.toString())
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
       Assert.assertNotNull(doctorRegistrationFragment)
    }

    private fun startFragment(fragment: Fragment, tagName: String) {
        val fragmentManager = mainActivity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(fragment, tagName)
        fragmentTransaction?.commit()
    }
}