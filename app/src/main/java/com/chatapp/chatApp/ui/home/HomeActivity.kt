package com.chatapp.chatApp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chatapp.Constants
import com.chatapp.R
import com.chatapp.chatApp.ui.addRoom.AddRoom
import com.chatapp.chatApp.ui.base.BaseActivity
import com.chatapp.chatApp.ui.chat.ChatActivity
import com.chatapp.database.model.Room
import com.chatapp.database.getRooms
import com.chatapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), Navigator {
    val adapter = RoomsAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewdataBinding.vm = viewModel
        viewModel.navigator = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int, room: Room) {
                startChatActivty(room)
            }

        }
        viewdataBinding.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        getRooms(
            onSuccessListener = {
                val rooms = it.toObjects(Room::class.java)
                adapter.changeData(rooms)
            },
            onFailureListener = {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    fun startChatActivty(room: Room) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(Constants.EXTRA_ROOM, room)
        startActivity(intent)
    }

    override fun goToCreateRoom() {
        val intent = Intent(this, AddRoom::class.java)
        startActivity(intent)
    }
}