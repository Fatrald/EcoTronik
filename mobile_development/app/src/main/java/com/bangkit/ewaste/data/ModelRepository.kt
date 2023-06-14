package com.bangkit.ewaste.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.network.ApiModel
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.PredictResponse
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.data.response.transaksi.TransaksiByImageRequest
import com.bangkit.ewaste.data.response.transaksi.UpdateTransaksiResponse
import com.bangkit.ewaste.ui.login.LoginActivity
import com.bangkit.ewaste.utils.SharedPreferences
import com.bangkit.ewaste.utils.showToast
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelRepository (private val context : Context, private val apiModel: ApiModel, private val apiService : ApiService){
    private val _predictValue = MutableLiveData<Int>()
    val predictValue : LiveData<Int> get() = _predictValue
    private val _ecotronikItem = MutableLiveData<EcotronikResponseItem>()
    val ecotronikItem : LiveData<EcotronikResponseItem> get() = _ecotronikItem

    fun predictImage(image : MultipartBody.Part){
        val call = apiModel.predictImage(image)
        call.enqueue(object : Callback<PredictResponse>{
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                if (response.isSuccessful){
                    _predictValue.value = response.body()?.class_number
                } else {
                    _predictValue.value = 999
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
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
                    _ecotronikItem.value = response.body()
                } else {
                    Log.e("GetEcotronikItem", "Failed : $response")
                }
            }

            override fun onFailure(call: Call<EcotronikResponseItem>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun createTransaksiByImage(uuid: String, elektronikId: Int, path: String) {
        val request = TransaksiByImageRequest("menunggu", "1", uuid, elektronikId, path)
        val call = apiService.createTransaksiByImage(request)
        call.enqueue(object : Callback<UpdateTransaksiResponse>{
            override fun onResponse(
                call: Call<UpdateTransaksiResponse>,
                response: Response<UpdateTransaksiResponse>
            ) {
                if (response.isSuccessful){
                    val intent = Intent(context, MainActivity::class.java)
                    context.showToast(response.body()?.msg.toString())
                    context.startActivity(intent)
                } else {
                    Log.e("CreateTransaksiByImage", "Failed : $response")
                }
            }

            override fun onFailure(call: Call<UpdateTransaksiResponse>, t: Throwable) {
                context.showToast("Data Gagal Dimuat, Periksa Koneksi Anda")
            }
        })
    }

    fun getUUID(): String {
        val sharedPref = SharedPreferences.initPreference(context, "localPref")
        val uuid = sharedPref.getString("token", "")
        return uuid.toString()
    }
}