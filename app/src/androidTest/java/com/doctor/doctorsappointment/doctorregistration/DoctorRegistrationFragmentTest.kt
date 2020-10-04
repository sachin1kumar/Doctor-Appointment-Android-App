package com.doctor.doctorsappointment.doctorregistration

import android.text.method.Touch.scrollTo
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.doctorregistration.view.DoctorRegistrationFragment
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class DoctorRegistrationFragmentTest {

    @Test fun testEventsFragment() {
        // The "fragmentArgs" and "factory" arguments are optional.

        val scenario = launchFragmentInContainer<DoctorRegistrationFragment>()
        onView(withId(R.id.tv_name))
            .perform(ViewActions.scrollTo(), click()).perform(ViewActions.typeTextIntoFocusedView("Sachin Doctor"))

    }

}