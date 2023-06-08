package com.bangkit.ewaste.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.LoginRequest
import java.util.UUID

class ProfileViewModel(private val repository : EcoRepository) : ViewModel() {

    val user = repository.user

    fun logoutUser() {
        repository.logoutUser()
    }

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun getUserByUUID(uuid : String) {
        repository.getUserByUUID(uuid)
    }

}