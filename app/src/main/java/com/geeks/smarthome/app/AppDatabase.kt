package com.geeks.smarthome.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.smarthome.data.local_db.dao.DoorDao
import com.geeks.smarthome.data.model.door.DoorEntity

@Database(entities = [DoorEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doorDao(): DoorDao
}