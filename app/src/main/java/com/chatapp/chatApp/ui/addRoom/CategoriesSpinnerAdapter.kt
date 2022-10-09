package com.chatapp.chatApp.ui.addRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.chatapp.R
import com.chatapp.chatApp.ui.model.Category

class CategoriesSpinnerAdapter(val items: List<Category>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(index: Int): Any {
        return items[index]
    }

    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun getView(index: Int, view: View?, container: ViewGroup?): View {
        var myView = view
        var viewHolder: ViewHolder
        if (myView == null) {
            myView = LayoutInflater.from(container?.context)
                .inflate(R.layout.item_spinner_category, container, false)
            viewHolder = ViewHolder(myView)
            myView.setTag(viewHolder)
        } else {
            viewHolder = myView.tag as ViewHolder
        }
        val item = items[index]
        viewHolder.title.text = item.name
        viewHolder.image.setImageResource(item.imageId!!)
        return myView!!
    }

    class ViewHolder(val view: View) {
        var image: ImageView = view.findViewById(R.id.image)
        var title: TextView = view.findViewById(R.id.title)
    }
}