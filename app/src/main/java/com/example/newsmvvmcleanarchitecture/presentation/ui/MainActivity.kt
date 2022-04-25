package com.example.newsmvvmcleanarchitecture.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsmvvmcleanarchitecture.R
import com.example.newsmvvmcleanarchitecture.databinding.ActivityMainBinding
import com.example.newsmvvmcleanarchitecture.presentation.adapter.NewsAdapter
import com.example.newsmvvmcleanarchitecture.presentation.viewmodel.NewsViewModel
import com.example.newsmvvmcleanarchitecture.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsViewModel: NewsViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)
        newsViewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
    }
}