package com.bangkit.ewaste.ui.edutron

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.model.EduTron
import com.bangkit.ewaste.data.model.EduTronData

class EduTronViewModel : ViewModel() {
    val eduTronList: MutableLiveData<List<EduTron>> = MutableLiveData()

    fun fetchEduTronData() {
        // Simulated data retrieval
        val eduTronData = EduTronData.EduTorn
        eduTronList.value = eduTronData
    }
}