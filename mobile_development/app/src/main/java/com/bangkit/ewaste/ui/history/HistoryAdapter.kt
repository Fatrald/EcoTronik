package com.bangkit.ewaste.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.user.TransactionResponse

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.TransactionViewHolder>() {
    private val transactionList: MutableList<TransactionResponse> = mutableListOf()

    fun setData(transactions: List<TransactionResponse>) {
        transactionList.clear()
        transactionList.addAll(transactions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaction: TransactionResponse) {
            // Bind the transaction data to the views in the item layout
            itemView.findViewById<TextView>(R.id.transaction_id).text = transaction.uuid_trx
            itemView.findViewById<TextView>(R.id.transaction_date).text = transaction.tglPost
            itemView.findViewById<TextView>(R.id.transaction_status).text = transaction.status
        }
    }
}
