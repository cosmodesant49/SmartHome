package com.geeks.smarthome.ui.door_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.smarthome.data.Repository
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.model.door.DoorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DoorViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<DoorModel>> = repository.getDoors()
}
