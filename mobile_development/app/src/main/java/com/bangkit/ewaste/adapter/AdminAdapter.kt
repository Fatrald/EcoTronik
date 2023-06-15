package com.bangkit.ewaste.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.transaksi.TransaksiByIdStatusItem
import com.bangkit.ewaste.data.response.user.UpdateUserPointRequest
import com.bangkit.ewaste.ui.admin.AdminViewModel
import com.bumptech.glide.Glide

class AdminAdapter(private val viewModel : AdminViewModel) : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    private var listTransaksi : MutableList<TransaksiByIdStatusItem> = mutableListOf()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemImage : ImageView = itemView.findViewById(R.id.item_image)
        val itemKode: TextView = itemView.findViewById(R.id.item_kode)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemCount: TextView = itemView.findViewById(R.id.item_count)
        val btnSubmit : Button = itemView.findViewById(R.id.btn_submit)
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
        holder.apply {
            itemKode.text = transaksi.uuid.takeLast(5)
            itemName.text = transaksi.jenisElektronik
            itemCount.text = "${transaksi.jmlh} perangkat"
            Glide.with(itemView)
                .load(transaksi.path) // Replace `transaksi.imageUrl` with the actual URL of the image
                .placeholder(R.drawable.ic_image) // Optional: placeholder image while loading
                .error(R.drawable.ic_image) // Optional: image to show if an error occurs
                .into(itemImage)
            btnSubmit.setOnClickListener {
                viewModel.getUserByUUID(transaksi.uuidUser)
                viewModel.user.observe(holder.itemView.context as LifecycleOwner){user ->
                    var newPoint = user.jmlPoint + transaksi.point
                    var updatePointUser = UpdateUserPointRequest(
                        user.uuid,
                        newPoint
                    )
                    viewModel.updatePoint(user.uuid, updatePointUser)
                }
                viewModel.updateStatusTransaksi(transaksi.uuid, "selesai")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<TransaksiByIdStatusItem>) {
        listTransaksi.clear()
        listTransaksi.addAll(newData)
        notifyDataSetChanged()
    }
}