package com.bangkit.ewaste.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.user.RowsItem

class HistoryAdapter(private var transactions: List<RowsItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]

        holder.transactionIdTextView.text = transaction.uuidTrx
        holder.transactionDateTextView.text = transaction.createdAt
        holder.transactionStatusTextView.text = transaction.status
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun updateData(transactions: List<RowsItem>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transactionIdTextView: TextView = itemView.findViewById(R.id.transaction_id)
        val transactionDateTextView: TextView = itemView.findViewById(R.id.transaction_date)
        val transactionStatusTextView: TextView = itemView.findViewById(R.id.transaction_status)
    }
}