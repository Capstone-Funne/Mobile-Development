package com.example.funne.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.lifecycle.lifecycleScope
import com.example.funne.R
import com.example.funne.data.model.FunneSession
import com.example.funne.data.model.LoginResponse
import com.example.funne.data.network.Result
import com.example.funne.data.request.LoginRequest
import com.example.funne.databinding.ActivityLoginBinding
import com.example.funne.di.ViewModelFactory
import com.example.funne.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAction()

        val login = FunneSession(this).isLoggedIn()
        if (login) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setMyButtonEnabled() {
        val btnLogin = binding.btnLogin
        btnLogin.isEnabled = binding.etEmail.isNotEmpty() && binding.etPassword.isNotEmpty()
    }

    private fun setUpAction() {
        binding.progressbar.visibility = View.GONE

        val pref = FunneSession(this)
        binding.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.editText?.text?.toString()
                val password = etPassword.editText?.text?.toString()
                if (email != null && password != null) {
                    lifecycleScope.launch {
                        loginViewModel.login(LoginRequest(email = email, password = password))
                            .observe(this@LoginActivity) {
                                if (it != null) {
                                    when (it) {
                                        is Result.Loading -> {
                                            binding.progressbar.visibility = View.VISIBLE
                                        }

                                        is Result.Success -> {
                                            binding.progressbar.visibility = View.GONE
                                            pref.saveUser(
                                                LoginResponse(
                                                    name = it.data?.name,
                                                    email = it.data?.email,
                                                    picture = it.data?.picture,
                                                    token = it.data?.token,
                                                    isLogin = true,
                                                ),
                                            )

                                            val intent =
                                                Intent(this@LoginActivity, MainActivity::class.java)
                                            intent.flags =
                                                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                            startActivity(intent)
                                            finish()

                                            Toast.makeText(
                                                this@LoginActivity,
                                                getString(R.string.login_success),
                                                Toast.LENGTH_SHORT,
                                            ).show()
                                        }

                                        is Result.Error -> {
                                            binding.progressbar.visibility = View.GONE
                                            Toast.makeText(
                                                this@LoginActivity,
                                                getString(R.string.login_error),
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

            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
