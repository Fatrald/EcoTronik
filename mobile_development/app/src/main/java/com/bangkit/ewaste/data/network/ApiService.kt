package com.bangkit.ewaste.data.network

import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponse
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem
import com.bangkit.ewaste.data.response.user.*
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
    fun getUserByUUID(@Path("uuid") uuid: String) : Call<TransactionResponse>

    @PATCH("users/{uuid}")
    fun updateUserByUUID(
        @Path("uuid") uuid: String,
        @Body updateUserRequest: UpdateUserRequest
    ) : Call<UpdateUserResponse>

    @GET("elektronik")
    fun getEcotronik() : Call<List<EcotronikResponseItem>>

<<<<<<< Updated upstream
    @GET("users/{uuid}")
    fun getTransactionByUUID(
        @Path("uuid") uuid: String
    ): Call<List<RowsItem>>
=======
    @GET("elektronik/{id}")
    fun getEcotronikById(
        @Path("id") id : String,
    ) : Call<EcotronikResponseItem>

    @GET("transaksi/{uuid}")
    fun getTransactionByUUID(
        @Path("uuid") uuid: String
    ): Call<TransactionResponse>

    @GET("transaksi/{uuid}/{status}")
    fun getTransaksiByStatus(
        @Path("uuid") uuid : String,
        @Path("status") status : String,
     ) : Call<List<TransaksiResponseItem>>

    @Headers("Content-Type: application/json")
    @POST("transaksi")
    fun postTransaksi(
        @Body transaksiRequest : TransaksiRequest
    ) : Call<PostTransaksiResponse>

>>>>>>> Stashed changes
}