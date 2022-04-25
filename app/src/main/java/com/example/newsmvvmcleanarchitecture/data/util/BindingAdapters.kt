package com.example.newsmvvmcleanarchitecture.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsmvvmcleanarchitecture.data.model.Article

@BindingAdapter("displayImage")
fun ImageView.setDisplayImage(item: Article) {
    Glide.with(this)
        .load(item.urlToImage)
        .into(this)
}