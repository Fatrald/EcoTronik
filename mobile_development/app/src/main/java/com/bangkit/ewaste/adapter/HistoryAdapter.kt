package com.bangkit.ewaste.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(private val data: List<TransaksiResponseItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = data[position]
        val transactionId = transaction.uuidTrx?.take(8) ?: ""
        holder.apply {
            transactionIdTextView.text = transactionId
            transactionDateTextView.text = formatDate(transaction.createdAt)
            transactionStatusTextView.text = transaction.status
            if (transaction.status == "proses") {
                transactionStatusTextView.setTextColor(Color.YELLOW)
            } else if(transaction.status == "menunggu") {
                transactionStatusTextView.setTextColor(Color.RED)
            } else {
                transactionStatusTextView.setTextColor(Color.GREEN)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
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