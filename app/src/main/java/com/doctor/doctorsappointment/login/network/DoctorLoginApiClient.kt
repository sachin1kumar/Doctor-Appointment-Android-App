package com.doctor.doctorsappointment.login.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DoctorLoginApiClient {

    //private const val BASE_URL = "http://10.0.2.2:8090"
    private const val BASE_URL = "http://192.168.1.5:8092"

    fun retrofitClient(): DoctorLoginApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(DoctorLoginApiInterface::class.java)
    }
}