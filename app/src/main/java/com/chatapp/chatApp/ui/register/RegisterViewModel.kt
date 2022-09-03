package com.chatapp.chatApp.ui.register

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class RegisterViewModel : BaseViewModel() {
    val firstName = ObservableField<String>()
    val firstNameError = ObservableField<String>()
    val lastName = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val userName = ObservableField<String>()
    val userNameError = ObservableField<String>()
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val auth = Firebase.auth
    fun createAccount() {

        if (validate()) {

            addAccountToFirebase()

        }

    }

    private fun addAccountToFirebase() {
        showLoding.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
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
        if (firstName.get().isNullOrBlank()) {
            firstNameError.set("Please Enter First Name ...")
            valid = false
        } else {
            firstNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()) {
            lastNameError.set("Please Enter Last Name ...")
            valid = false
        } else {
            lastNameError.set(null)
        }
        if (userName.get().isNullOrBlank()) {
            userNameError.set("Please Enter User Name ...")
            valid = false
        } else {
            userNameError.set(null)
        }
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