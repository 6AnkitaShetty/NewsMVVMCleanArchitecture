package com.example.newsmvvmcleanarchitecture.presentation.di

import com.example.newsmvvmcleanarchitecture.data.db.ArticleDAO
import com.example.newsmvvmcleanarchitecture.data.repository.dataSource.NewsLocalDataSource
import com.example.newsmvvmcleanarchitecture.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDAO)
    }
}