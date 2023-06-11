package com.bangkit.ewaste.ui.history

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.utils.EcoViewModelFactory


class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: HistoryViewModel
    private lateinit var transactionAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        // Initialize the RecyclerView and its adapter
        val rvTransaction: RecyclerView = findViewById(R.id.rv_transaction)
        rvTransaction.layoutManager = LinearLayoutManager(this)
        transactionAdapter = HistoryAdapter()
        rvTransaction.adapter = transactionAdapter

        // Observe the transaction list LiveData in the ViewModel
        viewModel.transactionList.observe(this, Observer { transactions ->
            // Update the adapter's data set when the transaction list changes
            transactionAdapter.setData(transactions)
        })

        // Call the getTransactionByUUID function with a sample UUID
        val uuid = "your_sample_uuid"
        val context: Context = this // Use the activity as the context
        viewModel.getTransactionByUUID(context, uuid)
    }
}