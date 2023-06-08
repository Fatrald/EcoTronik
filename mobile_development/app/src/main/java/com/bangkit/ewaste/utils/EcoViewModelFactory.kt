package com.bangkit.ewaste.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.di.Injection
import com.bangkit.ewaste.ui.login.LoginViewModel
import com.bangkit.ewaste.ui.post.PostWasteViewModel
import com.bangkit.ewaste.ui.profile.ProfileViewModel
import com.bangkit.ewaste.ui.registration.RegistrationViewModel

class EcoViewModelFactory(private val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PostWasteViewModel::class.java) -> {
                PostWasteViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}