package com.doctor.doctorsappointment.doctorregistration.model

import java.math.BigInteger

data class DoctorDetails(val name: String, val clinic_name: String, val address: String, val fees: BigInteger, val timing: String,
                         val registration_date: String, val email_id: String, val password: String)