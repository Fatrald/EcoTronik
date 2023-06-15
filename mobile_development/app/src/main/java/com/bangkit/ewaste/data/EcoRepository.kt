package com.bangkit.ewaste.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.network.ApiConfig
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.data.response.transaksi.*
import com.bangkit.ewaste.data.response.user.*
import com.bangkit.ewaste.ui.login.LoginActivity
import com.bangkit.ewaste.utils.SharedPreferences
import com.bangkit.ewaste.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class EcoRepository(private val context: Context, private val apiService: ApiService) {
    private val _listTransaksiAdmin = MutableLiveData<List<TransaksiByIdStatusItem>>()
    val listTransaksiAdmin : LiveData<List<TransaksiByIdStatusItem>> get() = _listTransaksiAdmin

    private val _user = MutableLiveData<TransactionResponse>()
    val user : LiveData<TransactionResponse> get() = _user

    private val _ecotronik = MutableLiveData<List<EcotronikResponseItem>>()
    val ecotronik : LiveData<List<EcotronikResponseItem>> get() = _ecotronik

    var ecotronikItem : EcotronikResponseItem ? = null

    private val _listTransaksi = MutableLiveData<List<TransaksiByIdStatusItem>>()
    val listTransaksi : LiveData<List<TransaksiByIdStatusItem>> get() = _listTransaksi

    private val _listHistory = MutableLiveData<List<TransaksiResponseItem>>()
    val listHistory: LiveData<List<TransaksiResponseItem>> get() = _listHistory

    fun registerUser(nama: String, email: String, password: String, confPassword: String) {
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
        call.enqueue(object : Callback<TransactionResponse> {
            override fun onResponse(call: Call<TransactionResponse>, response: Response<TransactionResponse>) {
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {

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

//    fun getTransactionHistory(uuid : String, status : String){
//        val call = apiService.getTransaksiHistory(uuid, status)
//        call.enqueue(object : Callback<List<TransaksiResponseItem>> {
//            override fun onResponse(
//                call: Call<List<TransaksiResponseItem>>,
//                response: Response<List<TransaksiResponseItem>>
//            ) {
//                if (response.isSuccessful) {
//                    val transactionList = response.body() ?: emptyList()
//                    _listHistory.value = transactionList
//                } else {
//                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<TransaksiResponseItem>>, t: Throwable) {
//                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
//            }
//        })
//
//    }

    fun getTransactionHistory(uuid : String){
        val call = apiService.getTransaksiHistory(uuid)
        call.enqueue(object : Callback<List<TransaksiResponseItem>> {
            override fun onResponse(
                call: Call<List<TransaksiResponseItem>>,
                response: Response<List<TransaksiResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val transactionList = response.body() ?: emptyList()
                    _listHistory.value = transactionList
                } else {
                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<TransaksiResponseItem>>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })

    }

    fun getEcotronik() {
        val call = apiService.getEcotronik()
        call.enqueue(object : Callback<List<EcotronikResponseItem>> {
            override fun onResponse(
                call: Call<List<EcotronikResponseItem>>,
                response: Response<List<EcotronikResponseItem>>
            ) {
                if (response.isSuccessful){
                    _ecotronik.value = response.body()
                } else {
                    Log.e("GETECOTRONIK","Failed : $response")
                }
            }

            override fun onFailure(call: Call<List<EcotronikResponseItem>>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }

        })
    }

    fun getEcotronikById(id : Int) {
        val call = apiService.getEcotronikById(id)
        call.enqueue(object : Callback<EcotronikResponseItem>{
            override fun onResponse(
                call: Call<EcotronikResponseItem>,
                response: Response<EcotronikResponseItem>
            ) {
                if (response.isSuccessful){
                    ecotronikItem = response.body()!!
                } else {
                    Log.e("GetEcotronikItem", "Failed : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EcotronikResponseItem>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun createTransaksi(state : String, count : Int, userUUID : String, wasteUUID : String){
        val transaksiRequest = TransaksiRequest(state, count, userUUID, wasteUUID)
        val call = apiService.postTransaksi(transaksiRequest)
        call.enqueue(object : Callback<PostTransaksiResponse>{
            override fun onResponse(
                call: Call<PostTransaksiResponse>,
                response: Response<PostTransaksiResponse>
            ) {
                if (response.isSuccessful){
                    getTransaksiByStatus(userUUID, "proses")
                    context.showToast("Transaksi Berhasil Ditambahkan")
                } else {
                    Log.e("postEcotronik","Failed : $response")
                }
            }

            override fun onFailure(call: Call<PostTransaksiResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun getTransaksiByStatus(uuid : String, status : String){
        val call = apiService.getTransaksiByStatus(uuid, status)
        call.enqueue(object : Callback<List<TransaksiByIdStatusItem>>{
            override fun onResponse(
                call: Call<List<TransaksiByIdStatusItem>>,
                response: Response<List<TransaksiByIdStatusItem>>
            ) {
                if (response.isSuccessful){
                    _listTransaksi.value = response.body()
                } else {
                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<TransaksiByIdStatusItem>>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun updateStatusTransaksi(uuid: String, s: String) {
        val updateTransaksiRequest = UpdateTransaksiRequest(uuid, s)
        val call = apiService.updateStatusTransaksi(updateTransaksiRequest)
        call.enqueue(object : Callback<UpdateTransaksiResponse>{
            override fun onResponse(
                call: Call<UpdateTransaksiResponse>,
                response: Response<UpdateTransaksiResponse>
            ) {
                if (response.isSuccessful){
                    context.showToast("Data berhasil di setor")
                } else {
                    Log.e("updateTransaksi", "failed : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UpdateTransaksiResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }


//    fun getTransaksiByStatus(uuid : String, status : String){
//        val call = apiService.getTransaksiByStatus(uuid, status)
//        call.enqueue(object : Callback<List<TransaksiResponseItem>>{
//            override fun onResponse(
//                call: Call<List<TransaksiResponseItem>>,
//                response: Response<List<TransaksiResponseItem>>
//            ) {
//                if (response.isSuccessful) {
//                    val filteredList = response.body()?.filter { transaction ->
//                        transaction.status == status
//                    }
//                    _listTransaksi.value = filteredList
//                } else {
//                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<TransaksiResponseItem>>, t: Throwable) {
//                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
//            }
//        })
//    }
//
////    fun getTransactionHistory(uuid : String){
////        val call = apiService.getTransactionByUUID(uuid)
////        call.enqueue(object : Callback<List<TransaksiResponseItem>>{
////            override fun onResponse(
////                call: Call<List<TransaksiResponseItem>>,
////                response: Response<List<TransaksiResponseItem>>
////            ) {
////                if (response.isSuccessful){
////                    _listTransaksi.value = response.body()
////                } else {
////                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
////                }
////            }
////
////            override fun onFailure(call: Call<List<TransaksiResponseItem>>, t: Throwable) {
////                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
////            }
////        })
////    }
//        private fun applyFilter(data: List<TransaksiResponseItem>?, status: String): List<TransaksiResponseItem> {
//            if (data.isNullOrEmpty()) {
//                return emptyList()
//            }
//
//            return if (status == "semua") {
//                // Return all transactions if the status is "All"
//                data
//            } else {
//                // Filter transactions based on the selected status
//                data.filter { transaction ->
//                    transaction.status == status
//                }
//            }
//        }


    fun upload(image : File){

    }

    fun getTransaksiAdmin(status: String) {
        val call = apiService.getTransaksiAdmin(status)
        call.enqueue(object : Callback<List<TransaksiByIdStatusItem>>{
            override fun onResponse(
                call: Call<List<TransaksiByIdStatusItem>>,
                response: Response<List<TransaksiByIdStatusItem>>
            ) {
                if (response.isSuccessful){
                    _listTransaksiAdmin.value = response.body()
                } else {
                    Log.e("getTransaksiByStatus", "failed : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<TransaksiByIdStatusItem>>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

}