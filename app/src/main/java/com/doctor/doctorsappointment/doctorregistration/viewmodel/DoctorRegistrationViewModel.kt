package com.doctor.doctorsappointment.doctorregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.doctorregistration.network.DoctorRegistrationApiClient
import com.doctor.doctorsappointment.utils.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DoctorRegistrationViewModel : ViewModel() {

    lateinit var doctorId: MutableLiveData<String>
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun validateAndRegisterDoctor(doctorDetails: DoctorDetails): MutableLiveData<String> {
        doctorId = MutableLiveData()
        val service = DoctorRegistrationApiClient.retrofitClient()
        try {
            uiScope.launch {
                val strDoctorDetails = service.registerDoctorAsync(doctorDetails)
                val response = strDoctorDetails.await()
                doctorId.value = response.body()?.doctorId.toString()
                PreferenceManager.loggedInSuccessfully(true)
            }
        } catch (e: Exception) {
            doctorId.value = "Registration Failed"
        }
        return doctorId
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}