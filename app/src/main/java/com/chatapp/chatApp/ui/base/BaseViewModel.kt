package com.chatapp.chatApp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val messageLiveData = MutableLiveData<String>()
    val showLoding = MutableLiveData<Boolean>()

}