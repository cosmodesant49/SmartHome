package com.geeks.smarthome.ui.camera_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.smarthome.databinding.FragmentCameraBinding
import com.geeks.smarthome.model.CameraModel
import com.geeks.smarthome.ui.camera_activity.adapter.CameraAdapter

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraAdapter: CameraAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cameraList = listOf(
            CameraModel("Камера 1"),
            CameraModel("Камера 2"),
            CameraModel("Камера 3")
        )

        cameraAdapter = CameraAdapter(cameraList)
        binding.rvCamera.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCamera.adapter = cameraAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
