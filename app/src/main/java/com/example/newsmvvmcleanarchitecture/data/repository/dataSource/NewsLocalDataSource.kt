package com.example.newsmvvmcleanarchitecture.data.repository.dataSource

import com.example.newsmvvmcleanarchitecture.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteArticleFromDb(article: Article)
}