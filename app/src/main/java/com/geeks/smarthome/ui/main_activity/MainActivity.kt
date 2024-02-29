package com.geeks.smarthome.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geeks.smarthome.databinding.ActivityMainBinding
import com.geeks.smarthome.ui.camera_activity.CameraFragment
import com.geeks.smarthome.ui.door_activity.DoorFragment
import com.geeks.smarthome.ui.main_activity.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CameraFragment(), "Camera")
        adapter.addFragment(DoorFragment(), "Doors")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}