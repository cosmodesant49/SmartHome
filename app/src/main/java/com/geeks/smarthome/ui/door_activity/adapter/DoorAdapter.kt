package com.geeks.smarthome.ui.door_activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.smarthome.databinding.ItemDoorBinding
import com.geeks.smarthome.model.DoorModel

class DoorAdapter(private var list: ArrayList<DoorModel>) : RecyclerView.Adapter<DoorAdapter.RecuclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecuclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoorBinding.inflate(inflater, parent, false)
        return RecuclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecuclerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class RecuclerViewHolder(private var binding: ItemDoorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemModel: DoorModel) = with(binding) {
            doorIv.load(itemModel.image)
            doorNameTv.text = itemModel.doorName
            doorNameTv.setOnClickListener {
                if (binding.doorIv.visibility == View.GONE) {
                    slideOutViews(binding.doorIv)
                } else {
                    slideInViews(binding.doorIv)
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
}