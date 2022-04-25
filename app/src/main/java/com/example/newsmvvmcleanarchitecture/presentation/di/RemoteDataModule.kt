package com.example.newsmvvmcleanarchitecture.presentation.di

import com.example.newsmvvmcleanarchitecture.data.api.NewsApiService
import com.example.newsmvvmcleanarchitecture.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsmvvmcleanarchitecture.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}