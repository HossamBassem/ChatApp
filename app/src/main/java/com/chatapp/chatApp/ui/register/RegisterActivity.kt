package com.chatapp.chatApp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.chatapp.R
import com.chatapp.chatApp.ui.base.BaseActivity
import com.chatapp.chatApp.ui.home.HomeActivity

import com.chatapp.databinding.ActivityRegisterBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>(), Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewdataBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}