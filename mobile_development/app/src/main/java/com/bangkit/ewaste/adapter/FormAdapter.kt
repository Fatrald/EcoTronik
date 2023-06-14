package com.bangkit.ewaste.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiByIdStatusItem

class FormAdapter : RecyclerView.Adapter<FormAdapter.ViewHolder>() {

    private var listTransaksi: MutableList<TransaksiByIdStatusItem> = mutableListOf()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemCount: TextView = itemView.findViewById(R.id.item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.waste_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<TransaksiByIdStatusItem>) {
        listTransaksi.clear()
        listTransaksi.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaksi = listTransaksi[position]
        holder.itemName.text = transaksi.jenisElektronik
        holder.itemCount.text = "${transaksi.jmlh} perangkat"

    }
}