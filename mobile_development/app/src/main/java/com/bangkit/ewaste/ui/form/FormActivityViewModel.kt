package com.bangkit.ewaste.ui.form

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository

class FormActivityViewModel(private val repository: EcoRepository) : ViewModel() {

    val listTransaksi = repository.listTransaksi


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