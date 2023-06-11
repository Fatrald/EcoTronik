package com.bangkit.ewaste.data.network

import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponse
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.data.response.user.LoginResponse
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import com.bangkit.ewaste.data.response.user.UpdateUserRequest
import com.bangkit.ewaste.data.response.user.UpdateUserResponse
import com.bangkit.ewaste.data.response.user.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ) : Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("users")
    fun register(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @GET("users/{uuid}")
    fun getUserByUUID(@Path("uuid") uuid: String) : Call<UserResponse>

    @PATCH("users/{uuid}")
    fun updateUserByUUID(
        @Path("uuid") uuid: String,
        @Body updateUserRequest: UpdateUserRequest
    ) : Call<UpdateUserResponse>

    @GET("elektronik")
    fun getEcotronik() : Call<EcotronikResponse>
}