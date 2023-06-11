package com.bangkit.ewaste.data.response.ecotronik

import com.google.gson.annotations.SerializedName

data class EcotronikResponse(

	@field:SerializedName("EcotronikResponse")
	val ecotronikResponse: List<EcotronikResponseItem>
)

data class EcotronikResponseItem(

	@field:SerializedName("uuid_elect")
	val uuidElect: String,

	@field:SerializedName("jenis_elektronik")
	val jenisElektronik: String,

	@field:SerializedName("point")
	val point: String
)
