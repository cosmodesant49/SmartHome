package com.geeks.smarthome.ui.camera_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.smarthome.databinding.FragmentCameraBinding
import com.geeks.smarthome.repository.Repository
import com.geeks.smarthome.ui.camera_activity.adapter.CameraAdapter
import kotlinx.coroutines.launch

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraAdapter: CameraAdapter
    private val repository = Repository() // Создание экземпляра репозитория

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val cameras = repository.fetchCameras() // Вызов метода на экземпляре репозитория
            if (cameras != null) {
                cameraAdapter = CameraAdapter(cameras)
                binding.rvCamera.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCamera.adapter = cameraAdapter
            } else {
                // Обработка ошибки загрузки данных
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
