package com.doctor.doctorsappointment.login.view

import android.content.Intent
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
import com.doctor.doctorsappointment.doctorregistration.view.DoctorRegistrationFragment
import com.doctor.doctorsappointment.doctorregistration.viewmodel.DoctorRegistrationViewModel
import com.doctor.doctorsappointment.home.HomeScreenActivity
import com.doctor.doctorsappointment.login.viewmodel.DoctorLoginViewModel
import com.doctor.doctorsappointment.utils.CustomProgress
import com.doctor.doctorsappointment.utils.PreferenceManager
import com.mindorks.example.coroutines.utils.Resource
import com.mindorks.example.coroutines.utils.Status
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import java.math.BigInteger

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        authorizeDoctor(view)
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

    private fun authorizeDoctor(view: View) {
        view.tv_authorize_doctor.setOnClickListener {
            val emailId = view.tv_login_email.text.toString()
            val password = view.tv_login_password.text.toString()
            val doctorDetails = DoctorDetails(
                "",
                "",
                "",
                BigInteger.ZERO,
                "",
                "",
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
        val customProgress = CustomProgress(requireActivity())
        val viewModel = initViewModel()
        (viewModel).getDoctorId().observe(viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        customProgress.dismissDialog()
                        it.data?.let { launchHomeScreen() }
                    }
                    Status.LOADING -> {
                        customProgress.startLoadingDialog()
                    }
                    Status.ERROR -> {
                        customProgress.dismissDialog()
                        it.message?.let { message -> showToast(message) }
                    }
                }
            })
        viewModel.validateAndAuthenticateDoctor(doctorDetails)
    }

    private fun launchHomeScreen() {
        PreferenceManager.loggedInSuccessfully(true)
        //launch home screen.
        val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
        startActivity(intent)
    }

    private fun initViewModel(): DoctorLoginViewModel {
        return ViewModelProviders.of(this)
            .get(DoctorLoginViewModel::class.java)
    }
}