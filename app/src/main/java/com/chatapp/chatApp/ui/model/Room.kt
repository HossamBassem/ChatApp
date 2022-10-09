package com.chatapp.chatApp.ui.model

import com.chatapp.chatApp.ui.model.Category.Companion.SPORTS

class Room(
    var id: String? = null,
    var name: String? = null,
    var desc: String? = null,
    var categoryId: String? = null
) {
    fun getCategoryImageId(): Int {
        return Category.fromId(categoryId ?: Category.SPORTS).imageId!!
    }

    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}