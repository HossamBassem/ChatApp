package com.chatapp.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Room(
    var id: String? = null,
    var name: String? = null,
    var desc: String? = null,
    var categoryId: String? = null
) : Parcelable {
    fun getCategoryImageId(): Int {
        return Category.fromId(categoryId ?: Category.SPORTS).imageId!!
    }

    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}