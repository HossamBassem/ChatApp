package com.chatapp.chatApp.ui.chat

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chatapp.Constants
import com.chatapp.R
import com.chatapp.chatApp.ui.base.BaseActivity
import com.chatapp.database.getMessagesRef
import com.chatapp.database.model.Message
import com.chatapp.databinding.ActivityChatBinding
import com.chatapp.database.model.Room
import com.google.firebase.firestore.Query
import com.google.firestore.v1.DocumentChange

class ChatActivity : BaseActivity<ChatViewModel, ActivityChatBinding>(), Navigator {
    lateinit var room: Room
    val adapter = MessagesAdapter()
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewdataBinding.vm = viewModel
        viewModel.navigator = this
        room = intent.getParcelableExtra(Constants.EXTRA_ROOM)!!
        viewModel.room = room
        viewdataBinding.recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        viewdataBinding.recyclerView.layoutManager = layoutManager
        listenForMessagesUpdates()

    }

    fun listenForMessagesUpdates() {
        getMessagesRef(room.id!!)
            .orderBy("dateTime", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Toast.makeText(this, "Cant retrieve messages", Toast.LENGTH_LONG)
                } else {
                    val newMessagesList = mutableListOf<Message>()
                    for (dc in snapshot!!.documentChanges) {
                        when (dc.type) {
                            com.google.firebase.firestore.DocumentChange.Type.ADDED -> {
                                val message = dc.document.toObject(Message::class.java)
                                newMessagesList.add(message)
                            }
                            else -> {
                                Toast.makeText(this, "we are in else", Toast.LENGTH_LONG)
                            }
                        }
                        adapter.appendMessages(newMessagesList)
                        viewdataBinding.recyclerView.smoothScrollToPosition(adapter.itemCount)
                    }
                }

            }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
}