package com.doctor.doctorsappointment.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.login.network.DoctorLoginApiClient
import com.doctor.doctorsappointment.utils.PreferenceManager
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DoctorLoginViewModel : ViewModel() {

    private var doctorId = MutableLiveData<Resource<String>>()
    private var detailsValidation = MutableLiveData<Resource<String>>()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun validateAndAuthenticateDoctor(doctorDetails: DoctorDetails) {
        val service = DoctorLoginApiClient.retrofitClient()
        try {
            uiScope.launch {
                doctorId.postValue(Resource.loading(null))
                val strDoctorDetails = service.loginDoctorAsync(doctorDetails)
                val response = strDoctorDetails.await()
                //save doctor id to shared pref..
                val strDocId = response.body()?.doctorId.toString()
                saveDoctorIdToPref(strDocId)
                //Thread.sleep(5000)
                doctorId.postValue(Resource.success(strDocId))
            }
        } catch (e: Exception) {
            doctorId.postValue(Resource.error("Login Failed", null))
        }
    }

    private fun saveDoctorIdToPref(strDocId: String) {
        PreferenceManager.saveDoctorId(strDocId)
    }

    fun validateForEmptyDetails(doctorDetails: DoctorDetails) {
        when {
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