package com.chatapp.chatApp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.chatapp.R
import com.chatapp.chatApp.ui.base.BaseActivity
import com.chatapp.chatApp.ui.base.BaseViewModel
import com.chatapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<BaseViewModel, ActivityHomeBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): BaseViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}