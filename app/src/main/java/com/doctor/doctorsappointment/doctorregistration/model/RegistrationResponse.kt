package com.doctor.doctorsappointment.doctorregistration.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class RegistrationResponse {

    @SerializedName("doctorId")
    @Expose
    var doctorId: BigInteger = BigInteger.ZERO

    override fun toString(): String {
        return "doctorId $doctorId"
    }
}