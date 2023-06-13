package com.example.funne.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funne.R
import com.example.funne.data.model.HistoryResponse
import com.example.funne.databinding.ListHistoryBinding

class HistoryAdapter(private val historyList: List<HistoryResponse?>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        historyList.reversed()[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class ViewHolder(val binding: ListHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(history: HistoryResponse) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(history.picture)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(ivHistory)

                tvDataIngredients.text = history.ingredients.toString()
                tvDataTerindikasi.text = history.results?.map { it?.name }?.joinToString(", ")
                tvTanggal.text = "Dibuat tanggal : " + history.createdAt.toString()
            }
        }
    }
}
