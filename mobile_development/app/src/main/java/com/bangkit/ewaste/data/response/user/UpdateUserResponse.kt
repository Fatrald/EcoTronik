package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
	val nama : String,
	val alamat : String,
	val email : String,
	val no_telp : String,
)

data class UpdateUserPointRequest(
    val point: String,
    val newPoint: Int,
)



data class UpdateUserResponse(

	@field:SerializedName("msg")
	val msg: String? = null
)
