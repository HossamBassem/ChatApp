package com.chatapp.chatApp.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.chatapp.database.addUserToFirestore
import com.chatapp.chatApp.ui.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : BaseViewModel<com.chatapp.chatApp.ui.register.Navigator>() {
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
                    // messageLiveData.value = "Successful Registration"
                    Log.e("done", "great")

                    createFirestoreUser(task.result.user?.uid)
                }

            }
    }

    fun createFirestoreUser(uid: String?) {
        Log.e("firestore", "fireeee")
        showLoding.value = true
        var user = AppUser(
            id = uid,
            firstName = firstName.get(),
            lastName = lastName.get(),
            userName = userName.get(),
            email = email.get()
        )
        Log.e("we", "here")
        addUserToFirestore(user,
            OnSuccessListener {
                Log.e("sssssss", "ssssssss")

                showLoding.value = false

                navigator?.openHomeScreen()

            },
            OnFailureListener {
                Log.e("nnnnnnnnnnnn", "mnnnnnnnn")
                showLoding.value = false
                messageLiveData.value = it.localizedMessage

            })
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