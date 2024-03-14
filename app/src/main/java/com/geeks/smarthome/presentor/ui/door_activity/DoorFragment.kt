package com.geeks.smarthome.presentor.ui.door_activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.geeks.smarthome.domain.app.App
import com.geeks.smarthome.domain.base.BaseFragment
import com.geeks.smarthome.databinding.FragmentDoorBinding
import com.geeks.smarthome.data.model.door.DoorEntity
import com.geeks.smarthome.data.model.door.DoorModel
import com.geeks.smarthome.presentor.ui.door_activity.adapter.DoorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DoorFragment : BaseFragment() {
    private lateinit var binding: FragmentDoorBinding
    private val viewModel: DoorViewModel by viewModels()
    private val adapter = DoorAdapter(true)
    private var list: List<DoorEntity> = mutableListOf()


/*
    var doorList = arrayListOf<DoorModel>(
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
        DoorModel("Door 1","https://i.kinja-img.com/image/upload/c_fit,q_60,w_645/opgj43no5dqhgafhypuk.jpg"),
    )

    private val adapter = DoorAdapter(doorList)*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoor.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.doorDao().getAll()
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
                val list = it.data
                Log.e("ololo", "List of doorModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.doorDao().clearAll()
                    list.forEach {
                        val door = DoorEntity(
                            favorites = it.favorites,
                            name = it.name,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "door: ${door.toString()}")
                        App.db.doorDao().insertDoor(door)

                    }
                    val listDB = App.db.doorDao().getAll()
                    Log.e("ololo", "List of doorEntiies: ${listDB.toString()}")
                    withContext(Dispatchers.Main) {
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }

}
