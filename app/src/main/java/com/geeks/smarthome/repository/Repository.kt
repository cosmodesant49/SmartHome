package com.geeks.smarthome.repository

import com.geeks.smarthome.api.AppApiService
import com.geeks.smarthome.model.camera.CameraModel
import com.geeks.smarthome.retrofit.CameraRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository {
    private val apiService = CameraRetrofit.instance

    suspend fun fetchCameras(): CameraModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCameras()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}