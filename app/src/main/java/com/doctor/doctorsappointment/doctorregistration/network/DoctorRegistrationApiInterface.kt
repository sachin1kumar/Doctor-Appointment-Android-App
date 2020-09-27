package com.doctor.doctorsappointment.doctorregistration.network

import com.doctor.doctorsappointment.doctorregistration.model.DoctorDetails
import com.doctor.doctorsappointment.doctorregistration.model.RegistrationResponse
import com.doctor.doctorsappointment.model.BookAppointment
import com.doctor.doctorsappointment.model.TotalReceivedResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.math.BigInteger

interface DoctorRegistrationApiInterface {

    @POST("/registration/registerDoctor/")
    fun registerDoctorAsync(@Body doctorDetails: DoctorDetails): Deferred<Response<RegistrationResponse>>
}