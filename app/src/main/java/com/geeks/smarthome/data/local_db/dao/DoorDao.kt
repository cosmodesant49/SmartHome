package com.geeks.smarthome.data.local_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geeks.smarthome.data.model.door.DoorEntity

@Dao
interface DoorDao {

    @Query("SELECT*FROM door")
    suspend fun getAll(): List<DoorEntity>

    @Insert
    suspend fun insertDoor(camera: DoorEntity)

    @Query("DELETE FROM door")
    suspend fun clearAll()
}