package com.bangkit.ewaste.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.adapter.HistoryAdapter
import com.bangkit.ewaste.databinding.ActivityHistoryBinding
import com.bangkit.ewaste.ui.admin.AdminViewModel
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.SharedPreferences


class HistoryActivity : AppCompatActivity() {

    private var _historyBinding : ActivityHistoryBinding? = null
    private val binding get() = _historyBinding!!

    private lateinit var viewModel : HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        viewModel.getTransaksiUser()
        viewModel.listTransaksi.observe(this){ data ->
            val adapter = HistoryAdapter(data)
            val layoutManager = LinearLayoutManager(this)
            binding.rvHistory.layoutManager = layoutManager
            binding.rvHistory.adapter = adapter
        }


    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
    }


}