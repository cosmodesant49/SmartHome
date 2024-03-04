package com.geeks.smarthome.retrofit

import com.geeks.smarthome.api.AppApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CameraRetrofit {
    private const val BASE_URL = "http://cars.cprogroup.ru/api/"

    val instance: AppApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(AppApiService::class.java)
    }
}
