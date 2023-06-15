package com.bangkit.ewaste.ui.redeem

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.model.Poin
import com.bangkit.ewaste.data.model.PoinData
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.user.TransactionResponse
import com.bangkit.ewaste.data.response.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RedeemViewModel(private val repository : EcoRepository): ViewModel() {


    private var _redeemList = MutableLiveData<List<Poin>>()
    val redeemList: LiveData<List<Poin>> = _redeemList

    val user = repository.user

    fun getUserByUUID(uuid: String) {
        repository.getUserByUUID(uuid)
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        val poinList = PoinData.Poin
        _redeemList.value = poinList
    }

    fun getUUID(): String {
        return repository.getUUID()
    }

}