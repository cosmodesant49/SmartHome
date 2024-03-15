package com.geeks.smarthome.presentor.ui.camera_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.smarthome.data.Repository
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.camera.CameraEntity
import com.geeks.smarthome.data.model.camera.CameraModel
import com.geeks.smarthome.data.model.door.DoorEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<CameraModel>> = repository.getCameras()


    fun deleteCamera(camera: CameraEntity) {
        viewModelScope.launch {
            repository.deleteCamera(camera)
        }
    }
}
