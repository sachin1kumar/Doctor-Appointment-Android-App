package com.doctor.doctorsappointment.doctorregistration.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.doctorregistration.viewmodel.DoctorRegistrationViewModel
import com.doctor.doctorsappointment.utils.Constants
import com.doctor.doctorsappointment.utils.CustomProgress
import com.mindorks.example.coroutines.utils.Status
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import java.math.BigInteger


class DoctorRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        registerDoctor(view)
        return view
    }

    private fun registerDoctor(view: View) {
        view.tv_register.setOnClickListener {
            val name = view.tv_name.text.toString()
            val clinicName = view.tv_clinic_name.text.toString()
            val clinicAddress = view.tv_clinic_address.text.toString()
            val fees = if (!view.tv_fees.text.toString().equals("")) view.tv_fees.text.toString().toBigInteger() else BigInteger.ZERO
            val timings = view.tv_timing.text.toString()
            val registrationDate = "19-SEPT-2020"
            val emailId = view.tv_email.text.toString()
            val password = view.tv_password.text.toString()
            val doctorDetails = DoctorDetails(
                name,
                clinicName,
                clinicAddress,
                fees,
                timings,
                registrationDate,
                emailId,
                password
            )
            if (!idEmptyFieldsPresent(doctorDetails)) {
                sendDetailsToNetwork(doctorDetails)
            }
        }
    }

    private fun idEmptyFieldsPresent(doctorDetails: DoctorDetails): Boolean {
        when {
            doctorDetails.name.equals("", true) -> {
                showToast("Name is empty")
                return true
            }
            doctorDetails.clinic_name.equals("", true) -> {
                showToast("Clinic name is empty")
                return true
            }
            doctorDetails.address.equals("", true) -> {
                showToast("Address is empty")
                return true
            }
            doctorDetails.fees == BigInteger.ZERO -> {
                showToast("Fees is empty")
                return true
            }
            doctorDetails.timing.equals("", true) -> {
                showToast("Timing is empty")
                return true
            }
            doctorDetails.email_id.equals("", true) -> {
                showToast("Email id is empty")
                return true
            }
            doctorDetails.password.equals("", true) -> {
                showToast("Password is empty")
                return true
            }
        }
        return false
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun sendDetailsToNetwork(doctorDetails: DoctorDetails) {
        val customProgress = CustomProgress()
        val viewModel = ViewModelProviders.of(this)
            .get(DoctorRegistrationViewModel::class.java)
        (viewModel).getDoctorId().observe(this,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        customProgress.show(requireFragmentManager(), "")
                        it.data?.let { doctorId -> launchHomeScreen(doctorId) }
                    }
                    Status.LOADING -> {
                        customProgress.show(requireFragmentManager(), "")
                    }
                    Status.ERROR -> {
                        customProgress.dismiss()
                        it.data?.let { doctorId -> showToast(doctorId) }
                    }
                }

            })

        viewModel.validateAndRegisterDoctor(doctorDetails)
    }

    private fun launchHomeScreen(doctorId: String) {
        val bundle = Bundle()
        bundle.putString(Constants.DOCTOR_ID_KEY, doctorId)
        findNavController().navigate(R.id.homeScreen, bundle)
    }

}