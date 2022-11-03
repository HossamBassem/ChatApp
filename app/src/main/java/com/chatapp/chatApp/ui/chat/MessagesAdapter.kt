package com.chatapp.chatApp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chatapp.R
import com.chatapp.chatApp.ui.DataUtils
import com.chatapp.database.model.Message
import com.chatapp.databinding.ItemRecievedMessageBinding
import com.chatapp.databinding.ItemSentMessageBinding

class MessagesAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<Message?>()


    class sentMessageViewHolder(val viewDataBinding: ItemSentMessageBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(message: Message?) {
            viewDataBinding.item = message
            viewDataBinding.executePendingBindings()
        }

    }

    class recievedMessageViewHolder(val viewDataBinding: ItemRecievedMessageBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(message: Message?) {
            viewDataBinding.item = message
            viewDataBinding.executePendingBindings()
        }

    }

    val RECIEVED = 1
    val SENT = 2
    override fun getItemViewType(position: Int): Int {
        val message = items.get(position)
        if (message?.senderId == DataUtils.user?.id) {
            return SENT
        }
        return RECIEVED

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (RECIEVED == viewType) {
            val itemBinding: ItemRecievedMessageBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recieved_message,
                    parent, false
                )

            return recievedMessageViewHolder(itemBinding)
        }
        val itemBinding: ItemSentMessageBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sent_message,
                parent, false
            )

        return sentMessageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is sentMessageViewHolder) {
            holder.bind(items.get(position))
        } else if (holder is recievedMessageViewHolder) {
            holder.bind(items.get(position))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun appendMessages(newMessageList: MutableList<Message>) {
        items.addAll(newMessageList)
        notifyItemRangeInserted(items.size + 1, newMessageList.size)
    }
}