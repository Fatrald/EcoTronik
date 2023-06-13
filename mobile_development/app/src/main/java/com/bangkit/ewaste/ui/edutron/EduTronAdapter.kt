package com.bangkit.ewaste.ui.edutron

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.model.EduTron
import com.squareup.picasso.Picasso

class EduTronAdapter : RecyclerView.Adapter<EduTronAdapter.EduTronViewHolder>() {
    private var eduTrons: List<EduTron> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EduTronViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edutron, parent, false)
        return EduTronViewHolder(view)
    }

    override fun onBindViewHolder(holder: EduTronViewHolder, position: Int) {
        val eduTron = eduTrons[position]
        holder.bind(eduTron)
    }

    override fun getItemCount(): Int {
        return eduTrons.size
    }

    fun setEduTrons(eduTrons: List<EduTron>) {
        this.eduTrons = eduTrons
        notifyDataSetChanged()
    }

    inner class EduTronViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val articleTextView: TextView = itemView.findViewById(R.id.articleTextView)

        fun bind(eduTron: EduTron) {
            // Load image using Picasso or any other image loading library
            Picasso.get().load(eduTron.imageLink).into(imageView)

            titleTextView.text = eduTron.title
            articleTextView.text = eduTron.article
        }
    }
}
