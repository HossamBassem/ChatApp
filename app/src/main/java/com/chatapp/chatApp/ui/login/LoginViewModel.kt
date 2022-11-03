package com.chatapp.chatApp.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import com.chatapp.chatApp.ui.DataUtils
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.chatapp.database.signIn
import com.chatapp.database.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel<Navigator>() {

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

    fun openRegister() {
        navigator?.openRegisterScreen()
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
                    //  navigator?.openHomeScreen()
                    //   messageLiveData.value = "Successful Registration"
                    Log.e("done", "great")
                    checkUserFromFirestore(task.result.user?.uid)

                }

            }
    }

    private fun checkUserFromFirestore(uid: String?) {
        showLoding.value = true
        signIn(uid!!,
            OnSuccessListener { docSnapshot ->
                showLoding.value = false
                val user = docSnapshot.toObject(AppUser::class.java)
                if (user == null) {
                    messageLiveData.value = "Invalid Email Or Password"
                    return@OnSuccessListener
                }
                DataUtils.user = user
                navigator?.openHomeScreen()
            },
            OnFailureListener {
                showLoding.value = false
                messageLiveData.value = it.localizedMessage

            })
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