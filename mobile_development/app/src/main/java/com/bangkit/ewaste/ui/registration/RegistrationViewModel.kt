package com.bangkit.ewaste.ui.registration

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.network.ApiService
import com.bangkit.ewaste.data.response.user.RegistrationRequest
import com.bangkit.ewaste.data.response.user.RegistrationResponse
import com.bangkit.ewaste.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel (private val Repository: EcoRepository) : ViewModel() {

    fun registerUser(nama: String, email: String, password: String, confPassword: String) {
        Repository.registerUser(nama, email, password, confPassword)
    }

    private fun showToast(message: String) {
        // Code to display a toast message in the UI
    }

    private fun showError(message: String) {
        // Code to display an error message in the UI
    }
}