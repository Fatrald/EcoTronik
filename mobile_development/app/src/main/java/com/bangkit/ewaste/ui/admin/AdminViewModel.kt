package com.bangkit.ewaste.ui.admin

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository

class AdminViewModel(private val repository : EcoRepository) : ViewModel() {
    val listTransaksi = repository.listTransaksiAdmin

    fun getTransaksiAdmin(status: String) {
        repository.getTransaksiAdmin(status)
    }

    fun updateStatusTransaksi(uuid: String, s: String) {
        repository.updateStatusTransaksi(uuid, s)
    }
}