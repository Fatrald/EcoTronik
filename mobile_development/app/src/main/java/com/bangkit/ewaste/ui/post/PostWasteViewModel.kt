package com.bangkit.ewaste.ui.post

import androidx.lifecycle.ViewModel
import com.bangkit.ewaste.data.ModelRepository
import okhttp3.MultipartBody

class PostWasteViewModel(private val modelRepository: ModelRepository) : ViewModel() {
    val ewasteId = modelRepository.predictValue
    val elektronikItem = modelRepository.ecotronikItem

    fun predictImage(image : MultipartBody.Part){
        modelRepository.predictImage(image)
    }

    fun getElektronnikById(id : Int){
        modelRepository.getEcotronikById(id)
    }
}