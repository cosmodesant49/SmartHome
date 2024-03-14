package com.geeks.smarthome.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.smarthome.data.local_db.dao.CameraDao
import com.geeks.smarthome.data.local_db.dao.DoorDao
import com.geeks.smarthome.data.model.camera.CameraEntity
import com.geeks.smarthome.data.model.door.DoorEntity

@Database(entities = [CameraEntity::class, DoorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao
}