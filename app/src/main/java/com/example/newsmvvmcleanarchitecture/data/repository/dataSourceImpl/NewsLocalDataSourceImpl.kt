package com.example.newsmvvmcleanarchitecture.data.repository.dataSourceImpl

import com.example.newsmvvmcleanarchitecture.data.db.ArticleDAO
import com.example.newsmvvmcleanarchitecture.data.model.Article
import com.example.newsmvvmcleanarchitecture.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticleFromDb(article: Article) {
        articleDAO.deleteArticle(article)
    }
}