package com.example.funne.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funne.data.model.SuggestionResponse
import com.example.funne.databinding.ItemSuggestionBinding

class SuggestionAdapter(private val suggestionList: List<SuggestionResponse?>) : RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        suggestionList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = suggestionList.size

    class ViewHolder(val binding: ItemSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(analysisResponse: SuggestionResponse) {
            binding.apply {
                tvSolusi.text = analysisResponse.title
                tvDescription.text = analysisResponse.description
            }
        }
    }
}