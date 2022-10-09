package com.chatapp.chatApp.ui.addRoom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.chatapp.R
import com.chatapp.chatApp.ui.base.BaseActivity
import com.chatapp.chatApp.ui.model.Category
import com.chatapp.databinding.ActivityAddRoomBinding

class AddRoom : BaseActivity<AddRoomViewModel, ActivityAddRoomBinding>(), Navigator {
    lateinit var adapter: CategoriesSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoriesSpinnerAdapter(viewModel.categoriesList)
        viewdataBinding.spinner.adapter = adapter
        viewdataBinding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.selectedCategory = viewModel.categoriesList[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    
                }

            }
        viewdataBinding.vm = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(
            this, { added ->
                if (added) {
                    showDialog(
                        "Room Added Successfully",
                        posActionName = "ok",
                        posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                            finish()
                        },
                        cancelable = false
                    )


                }

            }
        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
}