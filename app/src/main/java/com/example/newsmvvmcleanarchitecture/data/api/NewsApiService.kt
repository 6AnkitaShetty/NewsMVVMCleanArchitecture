package com.example.newsmvvmcleanarchitecture.data.api

import android.provider.SyncStateContract
import com.example.newsmvvmcleanarchitecture.data.model.ApiResponse
import com.example.newsmvvmcleanarchitecture.data.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<ApiResponse>
}