package com.bangkit.ewaste.ui.customviews

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository

class ManualFragmentViewModel (private val repository : EcoRepository) : ViewModel() {

    val ecotronik = repository.ecotronik

    fun getEcotronik() {
        repository.getEcotronik()
    }
    fun getUUID() : String {
        return repository.getUUID()
    }

    fun postTransaction(state: String, count: Int, userUUID: String, wasteUUID: String) {
        repository.createTransaksi(state, count, userUUID, wasteUUID)
    }
}