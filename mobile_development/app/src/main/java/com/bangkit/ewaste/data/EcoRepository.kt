package com.bangkit.ewaste.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import com.bangkit.ewaste.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EcoRepository(private val context: Context, private val apiService: ApiService) {

    fun registerUser(nama: String, email: String, password: String, confPassword: String, callback: (Boolean) -> Unit) {
        val registrationRequest = RegistrationRequest(nama, email, password, confPassword)
        val call = apiService.register(registrationRequest)

        call.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                    Log.d(ContentValues.TAG,response.message())
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}