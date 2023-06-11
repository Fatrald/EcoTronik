package com.bangkit.ewaste.ui.form

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import java.util.UUID

class FormActivityViewModel(private val repository: EcoRepository) : ViewModel() {

    val listTransaksi = repository.listTransaksi

    var ecotronikItem = repository.ecotronikItem

    fun getTransaksiByStatus(uuid: String, status : String) {
        repository.getTransaksiByStatus(uuid, status)
    }

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun getElektronikById(id: String) {
        return repository.getEcotronikById(id)
    }
}