package com.example.funne.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.funne.R
import com.example.funne.data.model.FunneSession
import com.example.funne.data.network.Result
import com.example.funne.databinding.FragmentProfileBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = FunneSession(requireContext()).getToken()
        binding.apply {
            lifecycleScope.launch {
                if (token != null) {
                    profileViewModel.getProfile(token = token).observe(viewLifecycleOwner){
                        if (it != null) {
                            when (it) {
                                is Result.Loading -> {
                                    binding.progressbar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    binding.progressbar.visibility = View.GONE

                                    tvNameProfile.text = it.data?.name
                                    tvGenderProfile.text = it.data?.gender.toString()
                                    Glide.with(this@ProfileFragment)
                                        .load(it.data?.picture)
                                        .circleCrop()
                                        .into(ivProfile)
                                }

                                is Result.Error -> {
                                    binding.progressbar.visibility = View.GONE

                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }





        /*
        val intent = Intent()
        val id = intent.getStringExtra(EXTRA_ID)
        if (id!= null) {
            lifecycleScope.launch {
                profileViewModel.getProfile()
            }
            profileViewModel.getProfile().observe(this) { ProfileResponse ->
                with(binding) {
                    tvNameProfile.text = ProfileResponse.name
                    tvGenderProfile.text = ProfileResponse.gender.toString()
                    Glide.with(this@ProfileFragment)
                        .load(ProfileResponse.picture)
                        .circleCrop()
                        .into(ivProfile)
                }
            }


        }

         */
    }

    /*
        companion object {
            const val EXTRA_ID = "extra_id"
        }


     */

}