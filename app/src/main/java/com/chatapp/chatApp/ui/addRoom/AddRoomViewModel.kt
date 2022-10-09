package com.chatapp.chatApp.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.chatapp.chatApp.ui.model.Category
import com.chatapp.chatApp.ui.model.Room
import com.chatapp.database.addRoom

class AddRoomViewModel : BaseViewModel<Navigator>() {
    val name = ObservableField<String>()
    val nameError = ObservableField<String>()
    val desc = ObservableField<String>()
    val descError = ObservableField<String>()
    val categoriesList = Category.getCategoriesList()
    var selectedCategory = categoriesList[0]
    val roomAdded = MutableLiveData<Boolean>()

    fun createRoom() {
        if (validate()) {
            val room = Room(
                name = name.get(),
                desc = desc.get(),
                categoryId = selectedCategory.id
            )
            showLoding.value = true
            addRoom(room,
                onSuccessListener = {
                    showLoding.value = false
                    roomAdded.value = true
                }, onFailureListener = {

                }

            )
        }


    }

    fun validate(): Boolean {
        var isValid = true
        if (name.get().isNullOrBlank()) {
            nameError.set("Please Enter Room Name...")
            isValid = false
        } else {
            nameError.set(null)
        }
        if (desc.get().isNullOrBlank()) {
            descError.set("Please Enter Room Description...")
            isValid = false
        } else {
            descError.set(null)
        }
        return isValid

    }
}