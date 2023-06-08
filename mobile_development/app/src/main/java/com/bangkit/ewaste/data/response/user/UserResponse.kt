package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("jml_point")
	val jmlPoint: Int? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
