package com.geeks.smarthome.ui.camera_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.smarthome.data.Repository
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.model.camera.CameraModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<CameraModel>> = repository.getCameras()
}
