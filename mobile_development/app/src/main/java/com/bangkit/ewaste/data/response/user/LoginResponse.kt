package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class LoginRequest(
	val email : String,
	val password : String,
)

data class LoginResponse(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("email")
	val email: String
)