package com.bangkit.ewaste.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.response.user.User

class ProfileViewModel(private val repository : EcoRepository) : ViewModel() {
    fun logoutUser() {
        repository.logoutUser()
    }

    private val _user = MutableLiveData<User>().apply {
        value = User(
            "M. Fidyan Fatra Aldino",
            "Jl. Abdul Wahab Gg. Nangka RT.06 RW.04, Kel. Sawangan Baru, Kec. Sawangan, Kota Depok, Jawa Barat",
            "fatraaldino@gmail.com",
//            "087700345684",
//            "user"
        )
    }
    val user: LiveData<User> = _user

}