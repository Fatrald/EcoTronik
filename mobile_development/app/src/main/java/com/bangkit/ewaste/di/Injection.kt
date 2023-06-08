package com.bangkit.ewaste.di

import android.content.Context
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): EcoRepository {
        val apiService = ApiConfig.getApiService(context)
        return EcoRepository(context,apiService)
    }
}
