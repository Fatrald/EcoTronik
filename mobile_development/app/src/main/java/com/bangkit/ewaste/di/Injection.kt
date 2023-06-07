package com.bangkit.ewaste.di

import android.content.Context
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.data.network.ApiConfig

class Injection {
    object Injection {
        fun provideRepository(context: Context): EcoRepository {
            val apiService = ApiConfig.getApiService(context)
            return EcoRepository(apiService)
        }
    }
}