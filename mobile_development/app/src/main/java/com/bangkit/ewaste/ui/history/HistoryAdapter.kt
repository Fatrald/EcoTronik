package com.bangkit.ewaste.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiByIdStatusItem
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem
import com.bangkit.ewaste.data.response.user.RowsItem
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(private var transactions: List<TransaksiResponseItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]

        val transactionId = transaction.uuidTrx?.take(8) ?: ""
        holder.transactionIdTextView.text = transactionId
        holder.transactionDateTextView.text = formatDate(transaction.createdAt)
        holder.transactionStatusTextView.text = transaction.status
    }

    override fun getItemCount(): Int {
        return transactions.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transactionIdTextView: TextView = itemView.findViewById(R.id.transaction_id)
        val transactionDateTextView: TextView = itemView.findViewById(R.id.transaction_date)
        val transactionStatusTextView: TextView = itemView.findViewById(R.id.transaction_status)


    }
    private fun formatDate(dateString: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val date = inputDateFormat.parse(dateString)
        return outputDateFormat.format(date)
    }
}