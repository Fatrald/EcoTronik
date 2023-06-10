package com.bangkit.ewaste.ui.profile

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.UpdateUserRequest

class EditProfileViewModel(private val repository : EcoRepository) : ViewModel() {

    val user = repository.user

    fun updateUser(uuid : String, userRequest: UpdateUserRequest){
        repository.updateUser(uuid, userRequest)
    }

    fun getUUID() : String {
        return repository.getUUID()
    }

    fun getUserByUUID(uuid : String) {
        repository.getUserByUUID(uuid)
    }
}