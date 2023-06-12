package com.bangkit.ewaste.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.transaksi.TransaksiByIdStatusItem

class FormActivityViewModel(private val repository: EcoRepository) : ViewModel() {

    val listTransaksi: LiveData<List<TransaksiByIdStatusItem>> = repository.listTransaksi


    fun getTransaksiByStatus(uuid: String, status : String) {
        repository.getTransaksiByStatus(uuid, status)
    }

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun submitForm(uuid: String, s: String) {
        repository.updateStatusTransaksi(uuid, s)
    }
}