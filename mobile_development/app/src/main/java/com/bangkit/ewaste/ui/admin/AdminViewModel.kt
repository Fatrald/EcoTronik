package com.bangkit.ewaste.ui.admin

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.UpdateUserPointRequest
import com.bangkit.ewaste.data.response.user.UpdateUserRequest

class AdminViewModel(private val repository : EcoRepository) : ViewModel() {
    val listTransaksi = repository.listTransaksiAdmin
    val user = repository.user

    fun getTransaksiAdmin(status: String) {
        repository.getTransaksiAdmin(status)
    }

    fun updateStatusTransaksi(uuid: String, s: String) {
        repository.updateStatusTransaksi(uuid, s)
    }

    fun getUUID(): String {
        return repository.getUUID()
    }

    fun getUserByUUID(uuid: String) {
        repository.getUserByUUID(uuid)
    }

    fun updatePoint(uuid : String, updateUserPointRequest: UpdateUserPointRequest){
        repository.updatePoint(uuid, updateUserPointRequest)
    }
}