package com.bangkit.ewaste.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponse
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.data.response.user.LoginResponse
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import com.bangkit.ewaste.data.response.user.UpdateUserRequest
import com.bangkit.ewaste.data.response.user.UpdateUserResponse
import com.bangkit.ewaste.data.response.user.UserResponse
import com.bangkit.ewaste.ui.login.LoginActivity
import com.bangkit.ewaste.utils.SharedPreferences
import com.bangkit.ewaste.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson

class EcoRepository(private val context: Context, private val apiService: ApiService) {
    private val _user = MutableLiveData<UserResponse>()
    val user : LiveData<UserResponse> get() = _user

    private val _ecotronik = MutableLiveData<List<EcotronikResponseItem>>()
    val ecotronik : LiveData<List<EcotronikResponseItem>> get() = _ecotronik

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
        return if (loginData.isNullOrEmpty()) {
            null
        } else {
            Gson().fromJson(loginData, LoginRequest::class.java)
        }
    }

    fun logoutUser() {
        SharedPreferences.logout(context)
        LoginActivity.open(context)
        context.showToast("Logout Success")
    }

    fun getUUID(): String {
        val sharedPref = SharedPreferences.initPreference(context, "localPref")
        val uuid = sharedPref.getString("token", "")
        return uuid.toString()
    }

    fun getUserByUUID(uuid: String) {
        val call = apiService.getUserByUUID(uuid)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun updateUser(uuid : String, updateUserRequest: UpdateUserRequest) {
        val call = apiService.updateUserByUUID(uuid, updateUserRequest)
        call.enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                if (response.isSuccessful) {
                    context.showToast(response.body()?.msg.toString())
                } else {
                    context.showToast("Update User Gagal")
                }
            }

            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }
    fun getTransactionHistory(){

    }

    fun getEcotronik() {
        val call = apiService.getEcotronik()
        call.enqueue(object : Callback<EcotronikResponse> {
            override fun onResponse(
                call: Call<EcotronikResponse>,
                response: Response<EcotronikResponse>
            ) {
                if (response.isSuccessful) {
                    _ecotronik.value = response.body()?.ecotronikResponse
                } else {
                    Log.e(ContentValues.TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<EcotronikResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }
}