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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funne.data.model.FunneSession
import com.example.funne.data.network.Result
import com.example.funne.databinding.FragmentHistoryBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.adapter.HistoryAdapter
import com.example.funne.ui.viewmodel.HistoryViewModel
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    private val historyViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = FunneSession(requireContext()).getToken()
        binding.apply {
            lifecycleScope.launch {
                if (token != null) {
                    historyViewModel.history(token = token).observe(viewLifecycleOwner) {
                        if (it != null) {
                            Log.d(ContentValues.TAG, "$it")
                            when (it) {
                                is Result.Loading -> {
                                    progressbar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    progressbar.visibility = View.GONE
                                    rvHistory.layoutManager = LinearLayoutManager(requireContext())
                                    rvHistory.adapter = HistoryAdapter(it.data)
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
