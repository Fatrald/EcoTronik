package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class RegistrationResponse (

    @field:SerializedName("msg")
    val msg: String? = null
)

data class RegistrationRequest(
    val nama: String,
    val email: String,
    val password: String,
    val confPassword: String
)