package com.bangkit.ewaste.data.response.transaksi

import com.google.gson.annotations.SerializedName

data class TransaksiResponse(

	@field:SerializedName("TransaksiResponse")
	val transaksiResponse: List<TransaksiResponseItem>
)

data class TransaksiResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("elektronikId")
	val elektronikId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("uuid_trx")
	val uuidTrx: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("jmlh")
	val jmlh: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
