package com.example.newsmvvmcleanarchitecture.presentation.di

import com.example.newsmvvmcleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.newsmvvmcleanarchitecture.data.repository.dataSource.NewsLocalDataSource
import com.example.newsmvvmcleanarchitecture.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsmvvmcleanarchitecture.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        newsLocalDataSource: NewsLocalDataSource,
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsLocalDataSource, newsRemoteDataSource)
    }
}