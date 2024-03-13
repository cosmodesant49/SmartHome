package mbk.io.sabrina_hm1_m7.ui.camera.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.smarthome.databinding.ItemCameraBinding
import com.geeks.smarthome.model.camera.CameraEntity

class CameraAdapter(private val isDoor : Boolean) :
    RecyclerView.Adapter<CameraAdapter.RecyclerViewHolder>() {

    private var cameraList: List<CameraEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemCameraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding, isDoor)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(cameraList[position])
    }

    override fun getItemCount(): Int = cameraList.size

    fun submitList(list: List<CameraEntity>) {
        cameraList = list
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(private val binding: ItemCameraBinding, private val isDoor: Boolean) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(camera: CameraEntity) {
            with(binding) {
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
