package com.geeks.smarthome.ui.door_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geeks.smarthome.databinding.FragmentDoorBinding
import com.geeks.smarthome.model.DoorModel
import com.geeks.smarthome.ui.door_activity.adapter.DoorAdapter

class DoorFragment : Fragment() {
    private lateinit var binding: FragmentDoorBinding



    var doorList = arrayListOf<DoorModel>(
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
    )

    private val adapter = DoorAdapter(doorList)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoor.adapter = adapter
    }

}
