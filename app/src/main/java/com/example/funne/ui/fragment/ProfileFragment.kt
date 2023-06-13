package com.example.funne.ui.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.funne.data.model.FunneSession
import com.example.funne.data.model.LoginResponse
import com.example.funne.data.network.Result
import com.example.funne.databinding.FragmentProfileBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.activity.LoginActivity
import com.example.funne.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var pref: FunneSession

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = FunneSession(requireContext())

        val token = FunneSession(requireContext()).getToken()
        binding.apply {
            lifecycleScope.launch {
                if (token != null) {
                    profileViewModel.getProfile(token = token).observe(viewLifecycleOwner) {
                        if (it != null) {
                            Log.d(ContentValues.TAG, "$it")
                            when (it) {
                                is Result.Loading -> {
                                    binding.progressbar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    binding.progressbar.visibility = View.GONE

                                    tvNameProfile.text = it.data?.name.toString()
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

            btnHistory.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToHistoryFragment()
                findNavController().navigate(action)
            }

            btnLogout.setOnClickListener {
                pref.saveUser(
                    LoginResponse(
                        name = null,
                        token = null,
                        isLogin = false,
                    ),
                )
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}
