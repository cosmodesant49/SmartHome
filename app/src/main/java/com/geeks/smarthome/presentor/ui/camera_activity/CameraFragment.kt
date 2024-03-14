package com.geeks.smarthome.presentor.ui.camera_activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geeks.smarthome.domain.app.App
import com.geeks.smarthome.domain.base.BaseFragment
import com.geeks.smarthome.databinding.FragmentCameraBinding
import com.geeks.smarthome.data.model.camera.CameraEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mbk.io.sabrina_hm1_m7.ui.camera.adapter.CameraAdapter

@AndroidEntryPoint
class CameraFragment : BaseFragment() {
    private lateinit var binding: FragmentCameraBinding
    private val viewModel: CameraViewModel by viewModels()
    private val adapter = CameraAdapter(false)
    private var list: List<CameraEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCamera.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.cameraDao().getAll()
            withContext(Dispatchers.Main) {
                if (list.isEmpty()) {
                    getData()
                } else {
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    fun getData() {
        viewModel.getCameras().stateHandler(
            success = { it ->
                val list = it.data.cameras
                Log.e("ololo", "List of cameraModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.cameraDao().clearAll()
                    list.forEach {
                        val camera = CameraEntity(
                            favorites = it.favorites,
                            name = it.name,
                            rec = it.rec,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "camera : ${camera.toString()}")
                        App.db.cameraDao().insertCamera(camera)
                    }
                    withContext(Dispatchers.Main) {
                        val listDB = App.db.cameraDao().getAll()
                        Log.e("ololo", "List of cameraEntiies: ${listDB.toString()}")
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
