package com.example.funne.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.funne.R
import com.example.funne.data.network.Result
import com.example.funne.data.request.RegisterRequest
import com.example.funne.databinding.ActivityRegisterBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.progressbar.visibility = View.GONE

        binding.apply {
            btnRegister.setOnClickListener {
                val name = etNama.editText?.text?.toString()
                val email = etEmail.editText?.text?.toString()
                val password = etPassword.editText?.text?.toString()
                if (name != null && email != null && password != null) {
                    lifecycleScope.launch {
                        registerViewModel.register(
                            RegisterRequest(name = name, email = email, password = password),
                        ).observe(this@RegisterActivity) {
                            if (it != null) {
                                when (it) {
                                    is Result.Loading -> {
                                        binding.progressbar.visibility = View.VISIBLE
                                    }
                                    is Result.Success -> {
                                        binding.progressbar.visibility = View.GONE
                                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                        startActivity(intent)
                                        Toast.makeText(
                                            this@RegisterActivity,
                                            resources.getString(R.string.register_success),
                                            Toast.LENGTH_SHORT,
                                        ).show()
                                    }
                                    is Result.Error -> {
                                        binding.progressbar.visibility = View.GONE
                                        Toast.makeText(
                                            this@RegisterActivity,
                                            resources.getString(R.string.register_error),
                                            Toast.LENGTH_SHORT,
                                        ).show()
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }
                }
            }
            tvLogin.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
