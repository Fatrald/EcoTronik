package com.bangkit.ewaste.data.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("transaksi")
	val transaksi: Transaksi,

	@field:SerializedName("no_telp")
	val noTelp: String,

	@field:SerializedName("jml_point")
	val jmlPoint: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String
)

data class Transaksi(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("rows")
	val rows: List<RowsItem?>
)

data class RowsItem(

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
