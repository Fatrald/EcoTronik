package com.bangkit.ewaste.ui.form

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.adapter.FormAdapter
import com.bangkit.ewaste.databinding.ActivityFormBinding
import com.bangkit.ewaste.ui.customviews.CustomDialogFragment
import com.bangkit.ewaste.ui.customviews.CustomDialogManualFragment
import com.bangkit.ewaste.ui.post.CameraActivity
import com.bangkit.ewaste.utils.ConstVal
import com.bangkit.ewaste.utils.ConstVal.KEY_FORM
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.showToast

class FormActivity : AppCompatActivity() {

    private var _activityFormBinding : ActivityFormBinding? = null
    private val binding get() = _activityFormBinding!!

    private lateinit var formViewModel : FormActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFormBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(_activityFormBinding?.root)

        val typeForm = intent.getStringExtra(KEY_FORM)

        if (typeForm == "auto"){
            binding.fabAdd.setOnClickListener {
                val intent = Intent(this, CameraActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else if (typeForm == "manual"){
            binding.fabAdd.setOnClickListener {
                val dialog = CustomDialogManualFragment()
                dialog.show(supportFragmentManager, "customDialog")
            }
        }

        setupViewModel()
        val uuid = formViewModel.getUUID()
        formViewModel.getTransaksiByStatus(uuid, "menunggu")
        formViewModel.listTransaksi.observe(this){
            val layoutManager = LinearLayoutManager(this)
            val adapter = FormAdapter(it)
            binding.rvForm.layoutManager = layoutManager
            binding.rvForm.adapter = adapter
        }
    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        formViewModel = ViewModelProvider(this, viewModelFactory)[FormActivityViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showToast("Form Disimpan di History")
    }

    companion object {
        fun start(context: Context, key : String) {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra(KEY_FORM, key)
            context.startActivity(intent)
        }
    }
}