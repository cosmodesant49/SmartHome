package com.geeks.smarthome.app

import android.app.Application
import androidx.room.Room
import com.geeks.smarthome.data.local_db.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}