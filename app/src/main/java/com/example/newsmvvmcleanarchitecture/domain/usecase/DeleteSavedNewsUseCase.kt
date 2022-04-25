package com.example.newsmvvmcleanarchitecture.domain.usecase

import com.example.newsmvvmcleanarchitecture.data.model.Article
import com.example.newsmvvmcleanarchitecture.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}