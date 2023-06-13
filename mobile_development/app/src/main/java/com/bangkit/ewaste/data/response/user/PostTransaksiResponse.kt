package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class PostTransaksiResponse(

	@field:SerializedName("msg")
	val msg: String
)

data class TransaksiRequest(
	val status : String,
	val jmlh : Int,
	val uuid : String,
	val uuid_elect : String
)
