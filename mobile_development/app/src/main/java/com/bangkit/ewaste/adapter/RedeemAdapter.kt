package com.bangkit.ewaste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.model.Poin
import com.bangkit.ewaste.data.model.PoinData
import com.bangkit.ewaste.data.response.user.UpdateUserPointRequest
import com.bangkit.ewaste.databinding.ItemRedeemBinding
import com.bangkit.ewaste.ui.redeem.RedeemViewModel
import com.bangkit.ewaste.utils.showToast
import com.bumptech.glide.Glide

class RedeemAdapter(private val viewModel : RedeemViewModel)  : RecyclerView.Adapter<RedeemAdapter.RedeemViewHolder>() {

    private val uuid = viewModel.getUUID()

    private var redeemList: List<Poin> = PoinData.Poin

    class RedeemViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val title : TextView = itemView.findViewById(R.id.titleTextView)
        val point : TextView = itemView.findViewById(R.id.poinTextView)
        val btnRedeem : Button = itemView.findViewById(R.id.klaimButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedeemViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_redeem, parent, false)
        return RedeemViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return redeemList.size
    }

    override fun onBindViewHolder(holder: RedeemAdapter.RedeemViewHolder, position: Int) {
        val redeem = redeemList[position]
        holder.apply {
            Glide.with(itemView)
                .load(redeem.imageLink)
                .into(imageView)
            title.text = redeem.title
            point.text = "${redeem.poin} EP"
            btnRedeem.setOnClickListener {
                viewModel.getUserByUUID(uuid)
                viewModel.user.observe(holder.itemView.context as LifecycleOwner){ user ->
                    if (user.jmlPoint >= redeem.poin){
                        var newPoint = user.jmlPoint - redeem.poin
                        var updatePointUser = UpdateUserPointRequest(
                            user.uuid,
                            newPoint
                        )
                        viewModel.updatePoint(user.uuid, updatePointUser)
                    } else {
                        holder.itemView.context.showToast("Maaf Point Tidak Cukup")
                    }
                }
            }
        }
    }

}