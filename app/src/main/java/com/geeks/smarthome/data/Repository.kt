package com.geeks.smarthome.data

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.geeks.smarthome.base.BaseRepository
import com.geeks.smarthome.data.api.AppApiService
import com.geeks.smarthome.data.model.camera.CameraModel
import com.geeks.smarthome.data.model.door.DoorModel
import javax.inject.Inject


class Repository @Inject constructor(private val api: AppApiService) : BaseRepository(api) {
    fun getCameras(): LiveData<com.geeks.smarthome.data.Resource<CameraModel>> = apiRequest {
        api.getCameras().body()!!
    }
    fun getDoors(): LiveData<com.geeks.smarthome.data.Resource<DoorModel>> = apiRequest {
        api.getDoors().body()!!
    }
}