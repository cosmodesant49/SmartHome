package com.geeks.smarthome.ui.camera_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.smarthome.databinding.ItemCameraBinding
import com.geeks.smarthome.model.camera.Camera
import com.geeks.smarthome.model.camera.CameraModel

class CameraAdapter(private val dataList: CameraModel?) :
    RecyclerView.Adapter<CameraAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCameraBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataList?.let { data ->
            val camera = data.data.cameras.firstOrNull()
            camera?.let { holder.bind(it) }
        }
    }

    override fun getItemCount(): Int {
        return if (dataList != null) {
            dataList.data.cameras.size
        } else {
            0
        }
    }

    inner class ViewHolder(private val binding: ItemCameraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(camera: Camera) {
            binding.cameraTv.text = camera.name
            Glide.with(binding.root)
                .load(camera.snapshot)
                .into(binding.videoIv)
        }
    }
}

