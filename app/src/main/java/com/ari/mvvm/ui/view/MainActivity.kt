package com.ari.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ari.mvvm.databinding.ActivityMainBinding
import com.ari.mvvm.ui.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this){ currentQuote ->
            binding.quote = currentQuote
        }

        quoteViewModel.isLoading.observe(this){ isLoading ->
            binding.isLoading = isLoading
        }

        binding.root.setOnClickListener { quoteViewModel.randomQuote() }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}