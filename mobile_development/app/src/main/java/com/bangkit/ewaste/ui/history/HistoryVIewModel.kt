package com.bangkit.ewaste.ui.history

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.network.ApiConfig
import com.bangkit.ewaste.data.response.user.RowsItem
import com.bangkit.ewaste.data.response.user.Transaksi
import com.bangkit.ewaste.data.response.user.TransactionResponse
import retrofit2.Call
import retrofit2.Response


class HistoryViewModel(private val context: Context) : ViewModel() {
    private val _transactionHistory = MutableLiveData<List<RowsItem>>()
    val transactionHistory: LiveData<List<RowsItem>> = _transactionHistory



    fun getHistoryTransaction(uuid: String) {
        val client = ApiConfig.getApiService(context).getTransactionByUUID(uuid)
        client.enqueue(object : retrofit2.Callback<List<RowsItem>>{

            override fun onResponse(
                call: Call<List<RowsItem>>,
                response: Response<List<RowsItem>>
            ) {
                if (response.isSuccessful) {
                    _transactionHistory.value = response.body()
                    Log.d(TAG, response.body().toString())
                }else {
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<RowsItem>>, t: Throwable) {
                Log.d(TAG,t.message.toString())
            }
        })
    }
}