package com.example.newsmvvmcleanarchitecture.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newsmvvmcleanarchitecture.R
import com.example.newsmvvmcleanarchitecture.databinding.FragmentInfoBinding
import com.example.newsmvvmcleanarchitecture.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private val args : InfoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInfoBinding = FragmentInfoBinding.inflate(layoutInflater)
        return fragmentInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.selectedArticle
        viewModel = (activity as MainActivity).newsViewModel
        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(article.url != null){
                loadUrl(article.url)
            }
        }
        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Saved Successfully!",Snackbar.LENGTH_LONG).show()
        }
    }

}