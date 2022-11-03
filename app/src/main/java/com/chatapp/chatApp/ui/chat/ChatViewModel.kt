package com.chatapp.chatApp.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.chatapp.chatApp.ui.DataUtils
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.chatapp.database.addMessage
import com.chatapp.database.model.Message
import com.chatapp.database.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import java.util.*

class ChatViewModel : BaseViewModel<Navigator>() {
    val messageField = ObservableField<String>()
    val toastLiveData = MutableLiveData<String>()
    var room: Room? = null

    fun sendMessage() {
        val message = Message(
            content = messageField.get(),
            roomId = room?.id,
            senderId = DataUtils.user?.id,
            senderName = DataUtils.user?.userName,
            dateTime = Date().time
        )
        // save message in firebase
        addMessage(message,
            OnSuccessListener {
                messageField.set("")

            }, OnFailureListener {
                toastLiveData.value = ("Something went worng ..")

            }
        )

    }
}