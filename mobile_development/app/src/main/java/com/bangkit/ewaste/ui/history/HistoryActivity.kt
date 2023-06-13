package com.bangkit.ewaste.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.databinding.ActivityHistoryBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.SharedPreferences


class HistoryActivity : AppCompatActivity() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var emptyTextView: TextView
    private lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        getUUID()
        setupSpinner()
        getHistory()

    }

    private fun setupSpinner() {

        val options = arrayOf("semua", "menunggu", "selesai") // Replace with your desired options
        val defaultStatus = "semua"
        val defaultStatusIndex = options.indexOf(defaultStatus)
        binding.filterSpinner.setSelection(defaultStatusIndex)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.filterSpinner.adapter = adapter


        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedStatus = options[position]
                historyViewModel.filterTransactionsByStatus(selectedStatus)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

    }

    private fun setupAction() {
        val viewModelFactory = EcoViewModelFactory(this)
        historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
    }

    private fun getUUID() {
        val sharedPrefs = SharedPreferences.initPreference(this, "localPref")
        uuid = sharedPrefs.getString("uuid", "") ?: ""
    }

    private fun getHistory() {
        emptyTextView= binding.emptyTextView
        val uuid = historyViewModel.getUUID()
        historyViewModel.getTransaksiHistory(uuid)
        historyViewModel.filterTransactionsByStatus("semua")
        historyViewModel.filteredTransaksi.observe(this) { transactions ->
            val layoutManager = LinearLayoutManager(this)
            val adapter = HistoryAdapter(transactions)
            binding.rvTransaction.layoutManager = layoutManager
            binding.rvTransaction.adapter = adapter

            if (transactions.isEmpty()) {
                emptyTextView.visibility = View.VISIBLE
            } else {
                emptyTextView.visibility = View.GONE
            }
        }
    }

}