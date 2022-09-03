package com.chatapp.chatApp.ui.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var viewdataBinding: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewdataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()
        subscribeToLiveData()


    }

    fun subscribeToLiveData() {
        viewModel.messageLiveData.observe(this) { message ->
            showDialog(message, "ok")
        }
        viewModel.showLoding.observe(this) { show ->
            if (show)
                showLoading()
            else hideLoading()
        }

    }

    var alertDialog: AlertDialog? = null
    fun showDialog(
        message: String,
        posActionName: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionName: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true
    ) {
        val defAction = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
            }


        }
        val builder = AlertDialog.Builder(this).setMessage(message)
        if (posActionName != null) {
            builder.setPositiveButton(posActionName, posAction ?: defAction)
        }
        if (negActionName != null) {
            builder.setNegativeButton(negActionName, negAction ?: defAction)
        }
        builder.setCancelable(cancelable)
        alertDialog = builder.show()
    }

    fun hideAlertDialog() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    var progressDialog: ProgressDialog? = null
    fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()

    }

    fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM
}