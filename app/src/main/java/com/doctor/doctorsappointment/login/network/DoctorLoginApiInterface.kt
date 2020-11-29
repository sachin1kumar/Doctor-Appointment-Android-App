package com.doctor.doctorsappointment.login.network

import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.login.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DoctorLoginApiInterface {

    @POST("/authorize/authorizeDoctor/")
    fun loginDoctorAsync(@Body doctorDetails: DoctorDetails): Deferred<Response<LoginResponse>>
}