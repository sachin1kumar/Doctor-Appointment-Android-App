package com.doctor.doctorsappointment.doctorregistration.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DoctorRegistrationApiClient {
    //private const val BASE_URL = "http://10.0.2.2:8090"
    private const val BASE_URL = "http://192.168.1.9:8081"

    fun retrofitClient(): DoctorRegistrationApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(DoctorRegistrationApiInterface::class.java)
    }
}