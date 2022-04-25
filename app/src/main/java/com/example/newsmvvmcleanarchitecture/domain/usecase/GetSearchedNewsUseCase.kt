package com.example.newsmvvmcleanarchitecture.domain.usecase


import com.example.newsmvvmcleanarchitecture.data.model.ApiResponse
import com.example.newsmvvmcleanarchitecture.data.util.Resource
import com.example.newsmvvmcleanarchitecture.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,searchQuery:String,page:Int): Resource<ApiResponse> {
        return newsRepository.getSearchedNews(country,searchQuery,page)
    }
}