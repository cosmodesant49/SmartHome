package com.geeks.smarthome.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.api.AppApiService
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository (private val api: AppApiService){
    fun <T> apiRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.Main) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                if (response != null) {
                    emit(Resource.Success(response))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }

        }

}