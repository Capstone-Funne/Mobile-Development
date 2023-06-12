package com.example.funne.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funne.data.model.AnalysisResponse
import com.example.funne.databinding.FragmentResultBinding
import com.example.funne.ui.adapter.IngredientsAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = arguments?.getString("count")
        val ingredients = arguments?.getParcelableArrayList<AnalysisResponse>("ingredients")
        binding.apply {
            countIngredients.text = count
            rvIngredients.layoutManager = LinearLayoutManager(context)
            rvIngredients.adapter = ingredients?.let { IngredientsAdapter(it) }
        }
    }

    companion object {
        const val TAG = "ResultFragment"
    }
}
