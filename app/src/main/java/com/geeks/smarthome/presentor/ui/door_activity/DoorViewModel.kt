package com.geeks.smarthome.presentor.ui.door_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.smarthome.data.Repository
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.door.DoorEntity
import com.geeks.smarthome.data.model.door.DoorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DoorViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<DoorModel>> = repository.getDoors()

    fun deleteDoor(door: DoorEntity) {
        viewModelScope.launch {
            repository.deleteDoor(door)
        }
    }
}
