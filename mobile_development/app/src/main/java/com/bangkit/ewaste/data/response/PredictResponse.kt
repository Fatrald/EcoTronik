package com.bangkit.ewaste.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("class_number")
	val class_number: Int
)
