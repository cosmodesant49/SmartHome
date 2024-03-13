package com.geeks.smarthome.ui.door_activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.smarthome.databinding.ItemCameraBinding
import com.geeks.smarthome.databinding.ItemDoorBinding
import com.geeks.smarthome.model.door.DoorEntity
import com.geeks.smarthome.model.door.DoorModel

class DoorAdapter(private val isDoor: Boolean) :
    ListAdapter<DoorEntity, DoorViewHolder>(DoorDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorViewHolder {
        return DoorViewHolder(
            ItemCameraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), isDoor
        )

    }

    override fun onBindViewHolder(holder: DoorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class DoorViewHolder(private var binding: ItemCameraBinding, private val isDoor: Boolean) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: DoorEntity) = with(binding) {
        videoIv.load(camera.snapshot)
        cameraTv.text = camera.name
        cameraTv.setOnClickListener {
            if (binding.videoIv.visibility == View.GONE) {
                slideOutViews(binding.videoIv)
            } else {
                slideInViews(binding.videoIv)
            }
        }
    }

    private fun slideOutViews(view: View) {
        val duration = 500L

        view.apply {
            visibility = View.VISIBLE
            translationY = -height.toFloat()

            animate()
                .translationY(0f)
                .setDuration(duration)
                .start()
        }
    }


    private fun slideInViews(view: View) {
        val duration = 500L

        view.apply {
            animate()
                .translationY(-height.toFloat())
                .setDuration(duration)
                .withEndAction {
                    visibility = View.GONE
                    translationY = 0f
                }
                .start()
        }
    }
}
class DoorDiffUtil : DiffUtil.ItemCallback<DoorEntity>() {
    override fun areItemsTheSame(oldItem: DoorEntity, newItem: DoorEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DoorEntity, newItem: DoorEntity): Boolean {
        return oldItem == newItem
    }

}