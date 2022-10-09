package com.chatapp.chatApp.ui.model

data class AppUser(
    var id: String? = null,
    var userName: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,

    ) {
    companion object {
        const val COLLECTION_NAME = "Users"
    }
}

