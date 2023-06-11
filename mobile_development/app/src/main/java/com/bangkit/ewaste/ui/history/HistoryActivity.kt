package com.bangkit.ewaste.ui.history

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.user.RowsItem
import com.bangkit.ewaste.databinding.ActivityHistoryBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.SharedPreferences


class HistoryActivity : AppCompatActivity() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var transactionList: List<RowsItem>
    private lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupAction()
        getUUID()
        getHistory()

//        val transactionList = createDummyTransactions()
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

        if (uuid != "") {
            historyViewModel.getHistoryTransaction(uuid)
            historyViewModel.transactionHistory.observe(this@HistoryActivity) { transactions ->
                transactionList = transactions
                historyAdapter.updateData(transactions)
                val listTransaksi : List<RowsItem> = transactionList
                historyAdapter = listTransaksi.let{ HistoryAdapter(it) }
                binding.rvTransaction.adapter = historyAdapter
            }
        } else {
            // Handle the case where UUID is not available
        }



    }

    private fun setupRecyclerView() {
//        historyAdapter = HistoryAdapter(transactionList)
        binding.rvTransaction.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
//            adapter = historyAdapter

        }
    }

//    private fun createDummyTransactions(): List<RowsItem> {
//        val transactions = mutableListOf<RowsItem>()
//
//        // Create dummy transactions
//        transactions.add(RowsItem("2023-06-10", "ecca0b6f-9054-483c-9c7d-4ca42a429215", "Success"))
//        transactions.add(RowsItem("2023-06-10", "ecca0b6f-9054-483c-9c7d-4ca42a429215", "Pending"))
//        transactions.add(RowsItem("2023-06-10", "ecca0b6f-9054-483c-9c7d-4ca42a429215", "Failed"))
//
//        return transactions
//    }
}