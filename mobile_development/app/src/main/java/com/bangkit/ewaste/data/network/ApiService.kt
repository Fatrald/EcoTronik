package com.bangkit.ewaste.data.network

import com.bangkit.ewaste.data.response.user.LoginResponse
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("users")
    fun register(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>
}