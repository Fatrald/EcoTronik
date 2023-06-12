package com.bangkit.ewaste.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem


class HistoryViewModel(private val repository: EcoRepository) : ViewModel() {

    val listHistory = repository.listHistory
    val filteredTransaksi = MutableLiveData<List<TransaksiResponseItem>>()

    fun getTransaksiHistory(uuid: String) {
        repository.getTransactionHistory(uuid)
    }

    fun filterTransactionsByStatus(status: String) {
        val transactions = listHistory.value ?: emptyList()
        val filteredList = if (status == "semua") {
            transactions // Return all transactions
        } else {
            transactions.filter { it.status == status } // Filter transactions based on the selected status
        }
        filteredTransaksi.value = filteredList
    }

    fun getUUID() : String {
        return repository.getUUID()
    }

}