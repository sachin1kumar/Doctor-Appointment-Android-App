package com.doctor.doctorsappointment.doctorregistration.viewmodel

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

class DoctorRegistrationViewModel : ViewModel() {

    private var doctorId = MutableLiveData<Resource<String>>()
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

    fun getDoctorId() : LiveData<Resource<String>> {
        return doctorId
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}