package com.example.funne.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funne.data.model.ProductResponse
import com.example.funne.databinding.ItemRecomendationBinding

class ProductAdapter(private val productList: List<ProductResponse?>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecomendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = productList.size

    class ViewHolder(val binding: ItemRecomendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productResponse: ProductResponse) {
            binding.apply {
                itemProductName.text = productResponse.name
                itemProductDescription.text = productResponse.description

                Glide.with(itemView)
                    .load(productResponse.picture)
                    .circleCrop()
                    .into(itemProductPhoto)
            }
        }
    }
}