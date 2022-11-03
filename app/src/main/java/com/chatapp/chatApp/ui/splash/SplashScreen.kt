package com.chatapp.chatApp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chatapp.R
import com.chatapp.chatApp.ui.DataUtils
import com.chatapp.chatApp.ui.home.HomeActivity
import com.chatapp.chatApp.ui.login.LoginActivity
import com.chatapp.database.model.AppUser
import com.chatapp.database.signIn
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            { checkLoggedInUser() }, 200
        )
    }

    private fun checkLoggedInUser() {
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser == null) {
            startLoginActivity()
        } else {
            // retrieve user from firestore
            signIn(firebaseUser.uid, OnSuccessListener { doc ->
                val user = doc.toObject(AppUser::class.java)
                DataUtils.user = user
                startHomeActivity()
            }, OnFailureListener {
                startLoginActivity()
            })

        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}