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
        Repository.registerUser(nama, email, password, confPassword) { success ->
            if (success) {
                // Registration successful, handle the response here
                // Perform any necessary actions or update the UI

                // For example, you can show a toast message
                showToast("Registration successful")
            } else {
                // Registration failed, handle the error here
                // You can display an error message or perform error-specific actions

                // For example, you can show an error message
                showError("Registration failed")
            }
        }
    }

    private fun showToast(message: String) {
        // Code to display a toast message in the UI
    }

    private fun showError(message: String) {
        // Code to display an error message in the UI
    }
}