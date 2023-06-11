package com.bangkit.ewaste.ui.history

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.network.ApiConfig
import com.bangkit.ewaste.data.network.ApiConfig.getApiService
import com.bangkit.ewaste.data.response.user.TransactionResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HistoryViewModel : ViewModel() {
    private val _transactionList = MutableLiveData<List<TransactionResponse>>()
    val transactionList: LiveData<List<TransactionResponse>> get() = _transactionList

    fun getTransactionByUUID(context: Context, uuid: String) {
        val apiService = getApiService(context)
        val call = apiService.getTransactionByUUID(uuid)

        call.enqueue(object : Callback<TransactionResponse> {
            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>
            ) {
                if (response.isSuccessful) {
                    val transaction = response.body()
                    if (transaction != null) {
                        _transactionList.value = listOf(transaction)
                    }
                } else {
                    // Handle the error case
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                // Handle the failure case
            }
        })
    }
}