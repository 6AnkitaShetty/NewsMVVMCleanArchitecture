package com.example.newsmvvmcleanarchitecture.domain.usecase

import com.example.newsmvvmcleanarchitecture.data.model.ApiResponse
import com.example.newsmvvmcleanarchitecture.data.util.Resource
import com.example.newsmvvmcleanarchitecture.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}