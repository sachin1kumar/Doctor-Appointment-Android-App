package com.doctor.doctorsappointment.doctorregistration.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.doctorregistration.viewmodel.DoctorRegistrationViewModel
import com.doctor.doctorsappointment.utils.Constants
import com.doctor.doctorsappointment.utils.CustomProgress
import com.doctor.doctorsappointment.utils.PreferenceManager
import com.mindorks.example.coroutines.utils.Resource
import com.mindorks.example.coroutines.utils.Status
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

    companion object {
        fun newInstance(): DoctorRegistrationFragment {
            val doctorRegistrationFragment = DoctorRegistrationFragment()
            val args = Bundle()
            doctorRegistrationFragment.arguments = args
            return doctorRegistrationFragment
        }
    }

    private fun registerDoctor(view: View) {
        view.tv_register.setOnClickListener {
            val name = view.tv_name.text.toString()
            val clinicName = view.tv_clinic_name.text.toString()
            val clinicAddress = view.tv_clinic_address.text.toString()
            val fees = if (!view.tv_fees.text.toString().equals("")) view.tv_fees.text.toString()
                .toBigInteger() else BigInteger.ZERO
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
            checkEmptyFieldsPresent(doctorDetails)
        }
    }

    private fun checkEmptyFieldsPresent(doctorDetails: DoctorDetails) {
        val viewModel = initViewModel()
        var isDisplayed = false

        val observer = Observer<Resource<String>> {
            when (it.status) {
                Status.ERROR -> {
                    if (!isDisplayed) {
                        it.message?.let { message -> showToast(message) }
                        isDisplayed = true
                    }
                }
                else -> {
                    sendDetailsToNetwork(doctorDetails)
                }
            }
            (viewModel).validateDetails().removeObservers(viewLifecycleOwner)
        }
        (viewModel).validateDetails().observe(viewLifecycleOwner, observer)
        (viewModel).validateForEmptyDetails(doctorDetails)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun sendDetailsToNetwork(doctorDetails: DoctorDetails) {
        val customProgress = CustomProgress()
        val viewModel = initViewModel()
        (viewModel).getDoctorId().observe(viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        customProgress.show(requireFragmentManager(), "")
                        it.data?.let { launchHomeScreen() }
                    }
                    Status.LOADING -> {
                        customProgress.show(requireFragmentManager(), "")
                    }
                    Status.ERROR -> {
                        customProgress.dismiss()
                        it.message?.let { message -> showToast(message) }
                    }
                }
            })
        viewModel.validateAndRegisterDoctor(doctorDetails)
    }

    private fun launchHomeScreen() {
        PreferenceManager.loggedInSuccessfully(true)
        //Thread.sleep(5000)
        findNavController().navigate(R.id.homeScreen)
    }

    private fun initViewModel(): DoctorRegistrationViewModel {
        return ViewModelProviders.of(this)
            .get(DoctorRegistrationViewModel::class.java)
    }

}