package com.doctor.doctorsappointment.doctorregistration.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.doctorregistration.network.DoctorRegistrationApiClient
import com.doctor.doctorsappointment.utils.PreferenceManager
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigInteger

class DoctorRegistrationViewModel : ViewModel() {

    private var doctorId = MutableLiveData<Resource<String>>()
    private var detailsValidation = MutableLiveData<Resource<String>>()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun validateAndRegisterDoctor(doctorDetails: DoctorDetails) {
        val service = DoctorRegistrationApiClient.retrofitClient()
        try {
            uiScope.launch {
                doctorId.postValue(Resource.loading(null))
                val strDoctorDetails = service.registerDoctorAsync(doctorDetails)
                val response = strDoctorDetails.await()
                doctorId.postValue(Resource.success(response.body()?.doctorId.toString()))
                PreferenceManager.loggedInSuccessfully(true)
            }
        } catch (e: Exception) {
            doctorId.postValue(Resource.error("Registration Failed", null))
        }
    }

    fun validateForEmptyDetails(doctorDetails: DoctorDetails) {
        when {
            doctorDetails.name.equals("", true) -> {
                postEmptyDetailsEvent("Name is Empty")
            }
            doctorDetails.clinic_name.equals("", true) -> {
                postEmptyDetailsEvent("Clinic name is empty")
            }
            doctorDetails.address.equals("", true) -> {
                postEmptyDetailsEvent("Address is empty")
            }
            doctorDetails.fees == BigInteger.ZERO -> {
                postEmptyDetailsEvent("Fees is empty")
            }
            doctorDetails.timing.equals("", true) -> {
                postEmptyDetailsEvent("Timing is empty")
            }
            doctorDetails.email_id.equals("", true) -> {
                postEmptyDetailsEvent("Email id is empty")
            }
            doctorDetails.password.equals("", true) -> {
                postEmptyDetailsEvent("Password is empty")
            }
            else -> {
                detailsValidation.postValue(Resource.success("Success"))
            }
        }
    }

    fun getDoctorId(): LiveData<Resource<String>> {
        return doctorId
    }

    fun validateDetails(): LiveData<Resource<String>> {
        return detailsValidation
    }

    private fun postEmptyDetailsEvent(message: String) {
        detailsValidation.postValue(Resource.error(message, null))
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}