package com.bangkit.ewaste.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem


class HistoryViewModel(private val repository: EcoRepository) : ViewModel() {

    val listTransaksi = repository.listTransaksiUser
    val listHistory = repository.listHistory

    fun getUUID() : String {
        return repository.getUUID()
    }

}