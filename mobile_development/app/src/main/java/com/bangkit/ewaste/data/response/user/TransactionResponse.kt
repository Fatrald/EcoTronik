package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
	@SerializedName("uuid_trx")
	val uuid_trx: String? = null,

	@field:SerializedName("tgl_post")
	val tglPost: String? = null,

	@field:SerializedName("elektronikId")
	val elektronikId: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("jmlh")
	val jmlh: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
