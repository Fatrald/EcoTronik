package com.bangkit.ewaste.ui.login

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.LoginRequest

class LoginViewModel(private val repository : EcoRepository) : ViewModel() {

    fun loginUser(email : String, password : String) {
        repository.loginUser(email, password)
    }

    fun setLoginData(loginData: LoginRequest) {
        repository.setLoginData(loginData)
    }

    fun getLoginData() : LoginRequest? {
        return repository.getLoginData()
    }
}