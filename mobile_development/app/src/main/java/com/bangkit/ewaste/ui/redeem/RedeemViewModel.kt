package com.bangkit.ewaste.ui.redeem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.model.Poin
import com.bangkit.ewaste.data.model.PoinData

class RedeemViewModel: ViewModel() {
    private val _redeemList = MutableLiveData<List<Poin>>()
    val redeemList: LiveData<List<Poin>> = _redeemList

    init {
        fetchData()
    }

    private fun fetchData() {
        // Retrieve the data from PoinData or any other data source
        val poinList = PoinData.Poin
        _redeemList.value = poinList
    }
}