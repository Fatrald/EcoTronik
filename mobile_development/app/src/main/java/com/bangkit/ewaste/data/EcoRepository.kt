package com.bangkit.ewaste.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.data.response.user.LoginResponse
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import com.bangkit.ewaste.ui.login.LoginActivity
import com.bangkit.ewaste.utils.SharedPreferences
import com.bangkit.ewaste.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson

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

    fun loginUser(email : String, password : String) {
        val loginRequest = LoginRequest(email, password)
        val call = apiService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    SharedPreferences.saveToken(response.body()?.uuid.toString(), context)
                    MainActivity.open(context)
                    context.showToast("Login Success")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                context.showToast("Login Gagal")
            }
        })
    }

    fun setLoginData(loginData: LoginRequest) {
        val json = Gson().toJson(loginData)
        SharedPreferences.saveLoginUser(json, context)
    }

    fun getLoginData() : LoginRequest? {
        val sharedPref = SharedPreferences.initPreference(context, "localPref")
        val loginData = sharedPref.getString("loginData", "")
        val json = loginData
        if (json.isNullOrEmpty()){
            return null
        } else {
            return Gson().fromJson(json, LoginRequest::class.java)
        }
    }

    fun logoutUser() {
        SharedPreferences.logout(context)
        LoginActivity.open(context)
        context.showToast("Logout Success")
    }
}