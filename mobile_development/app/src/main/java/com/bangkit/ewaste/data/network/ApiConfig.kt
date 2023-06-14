package com.bangkit.ewaste.data.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(context : Context) : ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.NONE)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone-ecotronik.et.r.appspot.com/")
//            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getApiModel(context : Context) : ApiModel {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.NONE)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ecotronik-model-nisrx5swhq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
        return retrofit.create(ApiModel::class.java)
    }
}