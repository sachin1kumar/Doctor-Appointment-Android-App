package com.doctor.doctorsappointment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doctor.doctorsappointment.model.BookAppointment
import com.doctor.doctorsappointment.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigInteger

class MyViewModel : ViewModel() {

    lateinit var totalCount: MutableLiveData<String>
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun getTotalReceivedCount() : MutableLiveData<String> {
        totalCount = MutableLiveData()
        val service = ApiClient.retrofitClient()

        uiScope.launch {
            /*val bookAppointment = BookAppointment("21-02-2020", BigInteger.valueOf(18))
            val strAdvice = service.getTotalAppointments(bookAppointment)
            val response = strAdvice.await()
            totalCount.value = response.body()?.totalRecResponse.toString()*/
        }
        return totalCount
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}