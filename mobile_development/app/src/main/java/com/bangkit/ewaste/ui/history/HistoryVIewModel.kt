package com.bangkit.ewaste.ui.history

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository


class HistoryViewModel(private val repository: EcoRepository) : ViewModel() {

    val listTransaksi = repository.listTransaksiUser

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun getTransaksiUser(uuid: String) {
        repository.getTransaksiByUUID(uuid)
    }

}