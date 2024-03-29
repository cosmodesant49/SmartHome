package com.geeks.smarthome.data.api

import com.geeks.smarthome.data.model.camera.CameraModel
import com.geeks.smarthome.data.model.door.DoorModel
import retrofit2.Response
import retrofit2.http.GET

interface AppApiService {
    @GET("cameras/")
    suspend fun getCameras(): Response<CameraModel>

    @GET("doors/")
    suspend fun getDoors(): Response<DoorModel>
}