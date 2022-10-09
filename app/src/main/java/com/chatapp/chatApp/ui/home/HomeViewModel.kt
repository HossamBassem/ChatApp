package com.chatapp.chatApp.ui.home

import com.chatapp.chatApp.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<Navigator>() {
    fun createRoom() {
        navigator?.goToCreateRoom()
    }
}