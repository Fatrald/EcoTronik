package com.bangkit.ewaste.data.response.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @field:SerializedName("userId")
     val userId: String,

     @field:SerializedName("name")
     val name: String,

     @field:SerializedName("token")
     val token: String
): Parcelable