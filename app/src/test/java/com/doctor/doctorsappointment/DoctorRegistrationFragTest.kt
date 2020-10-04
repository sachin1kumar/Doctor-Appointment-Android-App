package com.doctor.doctorsappointment

import com.doctor.doctorsappointment.MyTestRunner.startFragment
import com.doctor.doctorsappointment.doctorregistration.view.DoctorRegistrationFragment
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(MyTestRunner::class)
class DoctorRegistrationFragTest {

    @Before
    @Throws(java.lang.Exception::class)
    fun setUp() {
        SupportFragmentTestUtil.startVisibleFragment(mLoginFragment)
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        val yourFragment = DoctorRegistrationFragment()
        startFragment(yourFragment)
        assertNotNull(yourFragment)
    }
}