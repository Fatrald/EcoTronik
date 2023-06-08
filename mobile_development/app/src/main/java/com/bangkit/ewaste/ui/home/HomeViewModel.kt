package com.bangkit.ewaste.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.UserResponse

class HomeViewModel(private val repository: EcoRepository) : ViewModel() {

    val user : LiveData<UserResponse> get() = repository.user

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun getUserByUUID(uuid : String) {
        repository.getUserByUUID(uuid)
    }

}