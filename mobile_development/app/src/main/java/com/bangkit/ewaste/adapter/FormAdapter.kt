package com.bangkit.ewaste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiResponseItem
import com.bangkit.ewaste.ui.form.FormActivity
import com.bangkit.ewaste.ui.form.FormActivityViewModel

class FormAdapter(private val listTransaksi : List<TransaksiResponseItem>) : RecyclerView.Adapter<FormAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemCount: TextView = itemView.findViewById(R.id.item_count)
        val viewModel : FormActivityViewModel = ViewModelProvider(itemView.context as FormActivity).get(FormActivityViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.waste_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaksi = listTransaksi[position]
        holder.itemName.text = transaksi.elektronikId.toString()
        holder.itemCount.text = transaksi.jmlh.toString()
    }
}