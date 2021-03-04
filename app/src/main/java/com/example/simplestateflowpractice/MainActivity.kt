package com.example.simplestateflowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.simplestateflowpractice.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.loginUiState.collect {
                when (it) {
                    is LoginUiState.Success -> {

                        Snackbar.make(
                            binding.root,
                            "Successfully logged in!",
                            Snackbar.LENGTH_LONG
                        ).show()

                        binding.progressBar.isVisible = false
                    }
                    is LoginUiState.Error -> {

                        Snackbar.make(
                            binding.root,
                            it.message,
                            Snackbar.LENGTH_LONG
                        ).show()

                        binding.progressBar.isVisible = false

                    }
                    is LoginUiState.Loading ->{

                        binding.progressBar.isVisible = true

                    }
                    else -> Unit
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}