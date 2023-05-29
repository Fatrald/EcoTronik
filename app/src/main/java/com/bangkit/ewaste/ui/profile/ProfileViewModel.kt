package com.bangkit.ewaste.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.local.User

class ProfileViewModel : ViewModel() {
    private val _user = MutableLiveData<User>().apply {
        value = User(
            "M. Fidyan Fatra Aldino",
            "Jl. Abdul Wahab Gg. Nangka RT.06 RW.04, Kel. Sawangan Baru, Kec. Sawangan, Kota Depok, Jawa Barat",
            "fatraaldino@gmail.com",
            "087700345684"
        )
    }
    val user: LiveData<User> = _user
}