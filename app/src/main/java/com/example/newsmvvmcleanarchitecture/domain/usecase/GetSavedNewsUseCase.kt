package com.example.newsmvvmcleanarchitecture.domain.usecase

import com.example.newsmvvmcleanarchitecture.data.model.Article
import com.example.newsmvvmcleanarchitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository:NewsRepository) {

    fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}