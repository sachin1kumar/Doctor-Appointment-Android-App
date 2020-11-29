package com.doctor.doctorsappointment.login.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class LoginResponse {

    @SerializedName("doctorId")
    @Expose
    var doctorId: BigInteger = BigInteger.ZERO

    @SerializedName("errorMsg")
    @Expose
    var errorMsg: String = ""

    override fun toString(): String {
        return "doctorId $doctorId errorMsg $errorMsg"
    }
}