package com.bangkit.ewaste.ui.form

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.adapter.FormAdapter
import com.bangkit.ewaste.databinding.ActivityFormBinding
import com.bangkit.ewaste.ui.customviews.CustomDialogManualFragment
import com.bangkit.ewaste.ui.post.CameraActivity
import com.bangkit.ewaste.utils.ConstVal.KEY_FORM
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.showToast
import java.text.NumberFormat
import java.util.Locale

class FormActivity : AppCompatActivity() {

    private var _activityFormBinding : ActivityFormBinding? = null
    private val binding get() = _activityFormBinding!!

    private lateinit var formViewModel : FormActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFormBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(_activityFormBinding?.root)
        setupViewModel()

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

        val uuid = formViewModel.getUUID()
        formViewModel.getTransaksiByStatus(uuid, "menunggu")
        val adapter = FormAdapter(formViewModel.listTransaksi)
        val layoutManager = LinearLayoutManager(this)
        binding.rvForm.layoutManager = layoutManager
        binding.rvForm.adapter = adapter

        formViewModel.listTransaksi.observe(this) { data ->
            var point = 0
            data.map {
                point += it.point
            }
            val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            val formattedNumber = numberFormat.format(point)
            // Do something with the point value

            val wasteCount = data.size
            // Do something with the wasteCount value
            binding.tvPoint.text = formattedNumber.toString()
            binding.tvWasteSum.text = wasteCount.toString()

            binding.btnSetor.setOnClickListener {
                data.map {
                    formViewModel.submitForm(it.uuid, "proses")
                }
                FormResultActivity.start(this, "submit")
            }
        }

//        binding.btnSetor.setOnClickListener {
//            formViewModel.listTransaksi.observe(this) {data ->
//                data.map {
//                    formViewModel.submitForm(it.uuid, "proses")
//                }
//            }
//        }


    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        formViewModel = ViewModelProvider(this, viewModelFactory)[FormActivityViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showToast("Form Disimpan di History")
    }

    override fun onDestroy() {
        super.onDestroy()
        formViewModel.listTransaksi.removeObservers(this)
    }

    companion object {
        fun start(context: Context, key : String) {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra(KEY_FORM, key)
            context.startActivity(intent)
        }
    }
}