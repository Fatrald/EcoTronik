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

    fun getUserByUUID(email : String, password : String, uuid : String) {
        repository.getUserByUUID(email, password, uuid)
    }

    fun getLoginData() : LoginRequest? {
        return repository.getLoginData()
    }

}