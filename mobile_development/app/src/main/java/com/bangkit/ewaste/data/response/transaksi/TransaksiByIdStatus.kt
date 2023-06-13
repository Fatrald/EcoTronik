package com.bangkit.ewaste.data.response.transaksi

import com.google.gson.annotations.SerializedName

data class TransaksiByIdStatus(

	@field:SerializedName("TransaksiByIdStatus")
	val transaksiByIdStatus: List<TransaksiByIdStatusItem>
)

data class TransaksiByIdStatusItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("jenis_elektronik")
	val jenisElektronik: String,

	@field:SerializedName("point")
	val point : Int,

	@field:SerializedName("jmlh")
	val jmlh: Int,

	@field:SerializedName("status")
	val status: String
)
