package com.geeks.smarthome.ui.camera_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeks.smarthome.databinding.ItemCameraBinding
import com.geeks.smarthome.model.CameraModel

class CameraAdapter(private val dataList: List<CameraModel>) :
    RecyclerView.Adapter<CameraAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCameraBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cameraModel = dataList[position]
        holder.bind(cameraModel)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(private val binding: ItemCameraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cameraModel: CameraModel) {
            binding.cameraTv.text = cameraModel.cameraName

        }
    }
}

