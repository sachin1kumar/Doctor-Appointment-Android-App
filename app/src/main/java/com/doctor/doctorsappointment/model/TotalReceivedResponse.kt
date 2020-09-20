package com.doctor.doctorsappointment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class TotalReceivedResponse {

    @SerializedName("totalRecResponse")
    @Expose
    var totalRecResponse: BigInteger = BigInteger.ZERO

    override fun toString(): String {
        return "TotalReceivedResponse $totalRecResponse"
    }
}