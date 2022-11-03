package com.chatapp.chatApp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chatapp.R
import com.chatapp.database.model.Room
import com.chatapp.databinding.ItemRoomBinding

class RoomsAdapter(var items: List<Room?>?) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    class ViewHolder(val viewDataBinding: ItemRoomBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(room: Room?) {
            viewDataBinding.item = room
            viewDataBinding.invalidateAll()
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pos: Int, room: Room)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: ItemRoomBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_room, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
        onItemClickListener.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, items!![position]!!)

            }
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(rooms: List<Room>) {
        items = rooms
        notifyDataSetChanged()
    }
}