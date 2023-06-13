package com.bangkit.ewaste.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.ewaste.data.network.ApiModel
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.PredictResponse
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
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
}