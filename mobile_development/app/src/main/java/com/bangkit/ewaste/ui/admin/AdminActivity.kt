package com.bangkit.ewaste.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.R
import com.bangkit.ewaste.adapter.AdminAdapter
import com.bangkit.ewaste.data.EcoRepository
import com.bangkit.ewaste.databinding.ActivityAdminBinding
import com.bangkit.ewaste.ui.post.PostWasteViewModel
import com.bangkit.ewaste.utils.EcoViewModelFactory

class AdminActivity : AppCompatActivity() {

    private var _adminbinding : ActivityAdminBinding? = null
    private val binding get() = _adminbinding!!

    private lateinit var viewModel : AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _adminbinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        val adapter = AdminAdapter(viewModel)
        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaksi.layoutManager = layoutManager
        binding.rvTransaksi.adapter = adapter
        viewModel.getTransaksiAdmin("proses")
        viewModel.listTransaksi.observe(this){data ->
            adapter.notifyDataSetChanged()
            adapter.setData(data)
        }

    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AdminViewModel::class.java]
    }
}




