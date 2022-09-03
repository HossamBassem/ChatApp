package com.chatapp.chatApp.ui.login

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class LoginViewModel : BaseViewModel() {

    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val auth = Firebase.auth
    fun login() {

        if (validate()) {

            addAccountToFirebase()

        }

    }

    private fun addAccountToFirebase() {
        showLoding.value = true
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                showLoding.value = false
                if (!task.isSuccessful) {
                    messageLiveData.value = task.exception?.localizedMessage
                    //error message
                    Log.e("fail", "couldnt" + task.exception?.localizedMessage)
                } else {
                    //success message
                    messageLiveData.value = "Successful Registration"
                    Log.e("done", "great")
                }

            }
    }

    private fun validate(): Boolean {
        var valid = true

        if (email.get().isNullOrBlank()) {
            emailError.set("Please Enter Email ...")
            valid = false
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("Please Enter Password ...")
            valid = false
        } else {
            passwordError.set(null)
        }
        return valid
    }
}