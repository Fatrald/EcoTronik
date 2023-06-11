package com.bangkit.ewaste.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiByIdStatusItem
import org.w3c.dom.Text

class FormAdapter(private val listTransaksi: LiveData<List<TransaksiByIdStatusItem>>) : RecyclerView.Adapter<FormAdapter.ViewHolder>() {

    private var transaksiList: List<TransaksiByIdStatusItem> = emptyList()
    init {
        listTransaksi.observeForever { transaksiList ->
            this.transaksiList = transaksiList
            notifyDataSetChanged()
        }
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemCount: TextView = itemView.findViewById(R.id.item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.waste_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transaksiList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaksi = transaksiList[position]
        holder.itemName.text = transaksi.jenisElektronik
        holder.itemCount.text = "${transaksi.jmlh} perangkat"

    }
}