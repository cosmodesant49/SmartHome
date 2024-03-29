package com.geeks.smarthome.domain.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.geeks.smarthome.data.api.AppApiService
import com.geeks.smarthome.data.local_db.AppDatabase
import com.geeks.smarthome.data.local_db.dao.CameraDao
import com.geeks.smarthome.data.local_db.dao.DoorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cars.cprogroup.ru/api/rubetek/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideDoorDao(appDatabase: AppDatabase): DoorDao {
        return appDatabase.doorDao()
    }
    @Singleton
    @Provides
    fun provideCameraDao(appDatabase: AppDatabase): CameraDao {
        return appDatabase.cameraDao()
    }

    @Provides
    fun provideAppService(
        retrofit: Retrofit
    ): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }
}
