package com.example.funne.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funne.data.model.AnalysisResponse
import com.example.funne.databinding.ListIngredientsBinding

class IngredientsAdapter(private val ingredientsList: ArrayList<AnalysisResponse>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    inner class ViewHolder(private val binding: ListIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(analysisResponse: AnalysisResponse) {
            binding.apply {
                tvName.text = analysisResponse.name
                tvDescription.text = "Bahan Ke-" + (analysisResponse.id?.plus(1)).toString()
            }
        }
    }
}
