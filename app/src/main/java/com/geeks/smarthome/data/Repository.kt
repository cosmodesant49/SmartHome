package com.geeks.smarthome.data

import androidx.lifecycle.LiveData
import com.geeks.smarthome.domain.base.BaseRepository
import com.geeks.smarthome.data.api.AppApiService
import com.geeks.smarthome.data.local_db.dao.CameraDao
import com.geeks.smarthome.data.local_db.dao.DoorDao
import com.geeks.smarthome.data.model.camera.CameraEntity
import com.geeks.smarthome.data.model.camera.CameraModel
import com.geeks.smarthome.data.model.door.DoorEntity
import com.geeks.smarthome.data.model.door.DoorModel
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: AppApiService,
    private val doorDao: DoorDao,
    private val cameraDao: CameraDao
) : BaseRepository(api) {
    fun getCameras(): LiveData<Resource<CameraModel>> = apiRequest {
        api.getCameras().body()!!
    }

    fun getDoors(): LiveData<Resource<DoorModel>> = apiRequest {
        api.getDoors().body()!!
    }

    suspend fun deleteDoor(door: DoorEntity) {
        doorDao.deleteDoor(door)
    }
    suspend fun deleteCamera(camera: CameraEntity) {
        cameraDao.deleteCamera(camera)
    }
}