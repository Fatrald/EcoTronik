package com.bangkit.ewaste.data.response.transaksi

import com.google.gson.annotations.SerializedName

data class AdminTransaksiResponse(

	@field:SerializedName("AdminTransaksiResponse")
	val adminTransaksiResponse: List<AdminTransaksiResponseItem?>? = null
)

data class AdminTransaksiResponseItem(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("elektronikId")
	val elektronikId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("uuid_trx")
	val uuidTrx: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("jmlh")
	val jmlh: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
