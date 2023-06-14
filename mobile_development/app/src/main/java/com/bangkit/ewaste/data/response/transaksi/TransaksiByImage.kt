package com.bangkit.ewaste.data.response.transaksi

data class TransaksiByImageRequest (
    val status : String,
    val jmlh : String,
    val uuid : String,
    val elektronikId : Int,
    val path : String
)