package com.example.funne.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.funne.data.model.FunneSession
import com.example.funne.data.network.Result
import com.example.funne.databinding.FragmentAnalysisBinding
import com.example.funne.databinding.FragmentHomeBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.adapter.ProductAdapter
import com.example.funne.ui.adapter.SuggestionAdapter
import com.example.funne.ui.viewmodel.HomeViewModel
import com.example.funne.ui.viewmodel.SuggestionViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = FunneSession(requireContext()).getToken()
        binding.apply {
            lifecycleScope.launch {
                if (token != null) {
                    homeViewModel.getProfile(token = token).observe(viewLifecycleOwner){
                        if (it != null) {
                            Log.d(ContentValues.TAG, "$it")
                            when (it) {
                                is Result.Loading -> {
                                }

                                is Result.Success -> {
                                    name.setText(it.data?.name.toString())
                                    Glide.with(this@HomeFragment)
                                        .load(it.data?.picture)
                                        .circleCrop()
                                        .into(ivUser)
                                }

                                is Result.Error -> {
                                }

                                else -> {}
                            }
                        }
                    }

                    homeViewModel.product(token = token).observe(viewLifecycleOwner) {
                        if (it != null) {
                            Log.d(ContentValues.TAG, "$it")
                            when (it) {
                                is Result.Loading -> {
                                    progressbar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    progressbar.visibility = View.GONE
                                    rvRecommendProducts.layoutManager = LinearLayoutManager(requireContext())
                                    rvRecommendProducts.adapter = ProductAdapter(it.data)
                                }

                                is Result.Error -> {
                                    progressbar.visibility = View.GONE
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}
