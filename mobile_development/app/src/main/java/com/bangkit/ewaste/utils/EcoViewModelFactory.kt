package com.bangkit.ewaste.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.di.Injection
import com.bangkit.ewaste.ui.customviews.ManualFragmentViewModel
import com.bangkit.ewaste.ui.history.HistoryViewModel
import com.bangkit.ewaste.ui.form.FormActivityViewModel
import com.bangkit.ewaste.ui.home.HomeViewModel
import com.bangkit.ewaste.ui.login.LoginViewModel
import com.bangkit.ewaste.ui.post.PostWasteViewModel
import com.bangkit.ewaste.ui.profile.EditProfileViewModel
import com.bangkit.ewaste.ui.profile.ProfileViewModel
import com.bangkit.ewaste.ui.registration.RegistrationViewModel

class EcoViewModelFactory(private val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
                EditProfileViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(ManualFragmentViewModel::class.java) -> {
                ManualFragmentViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(FormActivityViewModel::class.java) -> {
                FormActivityViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(PostWasteViewModel::class.java) -> {
                PostWasteViewModel(Injection.modelRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}