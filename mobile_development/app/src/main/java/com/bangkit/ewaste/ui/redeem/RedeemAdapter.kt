package com.bangkit.ewaste.ui.redeem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.data.model.Poin
import com.bangkit.ewaste.databinding.ItemRedeemBinding

class RedeemAdapter  : RecyclerView.Adapter<RedeemAdapter.RedeemViewHolder>() {
    private var redeemList: List<Poin> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedeemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRedeemBinding.inflate(inflater, parent, false)
        return RedeemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedeemViewHolder, position: Int) {
        val poin = redeemList[position]
        holder.bind(poin)
    }

    override fun getItemCount(): Int {
        return redeemList.size
    }

    fun setData(redeemList: List<Poin>) {
        this.redeemList = redeemList
        notifyDataSetChanged()
    }

    inner class RedeemViewHolder(private val binding: ItemRedeemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(poin: Poin) {
            binding.imageView.setImageResource(poin.imageLink)
            binding.titleTextView.text = poin.title
            binding.poinTextView.text = "${poin.poin} Poin"

            binding.klaimButton.setOnClickListener {
                // Handle button click event here
            }
        }
    }
}