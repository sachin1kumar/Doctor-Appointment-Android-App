package com.doctor.doctorsappointment.network

import com.doctor.doctorsappointment.model.BookAppointment
import com.doctor.doctorsappointment.model.TotalReceivedResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.math.BigInteger

interface ApiInterface {

    @POST("/total-received-appointments/")
    fun getTotalAppointments(@Body bookAppointment: BookAppointment): Deferred<Response<TotalReceivedResponse>>
}