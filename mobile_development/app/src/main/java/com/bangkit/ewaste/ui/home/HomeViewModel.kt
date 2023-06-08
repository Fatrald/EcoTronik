package com.bangkit.ewaste.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.data.response.user.UserResponse

class HomeViewModel(private val repository: EcoRepository) : ViewModel() {

    val user = repository.user

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