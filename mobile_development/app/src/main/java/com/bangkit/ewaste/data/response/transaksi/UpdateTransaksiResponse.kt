package com.bangkit.ewaste.data.response.transaksi

import com.google.gson.annotations.SerializedName

data class UpdateTransaksiRequest(
	val uuid : String,
	val status : String
)

data class UpdateTransaksiResponse(

	@field:SerializedName("msg")
	val msg: String? = null
)
