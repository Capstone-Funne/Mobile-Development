package com.example.funne.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.funne.R
import com.example.funne.data.model.FunneSession
import com.example.funne.data.network.Result
import com.example.funne.data.request.AnalysisRequest
import com.example.funne.databinding.FragmentAnalysisBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.viewmodel.AnalysisViewModel
import kotlinx.coroutines.launch

class AnalysisFragment : Fragment() {
    private lateinit var binding: FragmentAnalysisBinding
    private val analysisViewModel by viewModels<AnalysisViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    private val args: AnalysisFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredients = args.response?.text
        binding.apply {
            progressbar.visibility = View.GONE
            etIngredients.setText(ingredients)
            btnAnalisis.setOnClickListener {
                analyzeImage()
            }
        }
    }

    private fun analyzeImage() {
        val token = FunneSession(requireContext()).getToken()
        binding.apply {
            val ingredients = etIngredients.text.toString()
            val imageId = args.response?.imageId.toString()
            lifecycleScope.launch {
                analysisViewModel.analyzeImage("Bearer $token", AnalysisRequest(ingredients, imageId)).observe(viewLifecycleOwner) { AnalysisResult ->
                    if (AnalysisResult != null) {
                        when (AnalysisResult) {
                            is Result.Loading -> {
                                progressbar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                progressbar.visibility = View.GONE
                                etIngredients.setText("")
                                val count = if (!args.response?.text.isNullOrEmpty()) {
                                    "${AnalysisResult.data.count()}/${args.response?.text?.count { it == ',' }?.plus(1)}"
                                } else {
                                    "${AnalysisResult.data.count()}/${ingredients.count { it == ',' }.plus(1)}"
                                }

                                val bundle = Bundle()
                                bundle.putString("count", count)
                                bundle.putParcelableArrayList("ingredients", ArrayList(AnalysisResult.data))

                                val modalBottomSheet = ResultFragment()
                                modalBottomSheet.arguments = bundle
                                modalBottomSheet.show(childFragmentManager, ResultFragment.TAG)
                            }

                            is Result.Error -> {
                                progressbar.visibility = View.GONE
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.error_scanning_image),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }

                            else -> {
                                Toast.makeText(requireContext(), "Silakan masukkan berkas teks terlebih dahulu.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}
