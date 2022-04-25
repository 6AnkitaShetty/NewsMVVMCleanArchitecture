package com.example.newsmvvmcleanarchitecture.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.newsmvvmcleanarchitecture.data.model.ApiResponse
import com.example.newsmvvmcleanarchitecture.data.model.Article
import com.example.newsmvvmcleanarchitecture.data.util.Resource
import com.example.newsmvvmcleanarchitecture.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {

    //top news
    val newsHeadlines: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                    newsHeadlines.postValue(apiResult)
                } else {
                    newsHeadlines.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                newsHeadlines.postValue(Resource.Error(e.message.toString()))
            }
        }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }

        }
        return false
    }

    //search news
    val searchedNews: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun searchNews(
        country: String,
        searchQuery: String,
        page: Int
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            searchedNews.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getSearchedNewsUseCase.execute(country, searchQuery, page)
                    searchedNews.postValue(apiResult)
                } else {
                    searchedNews.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                searchedNews.postValue(Resource.Error(e.message.toString()))
            }
        }

    // local data
    fun saveArticle(article: Article) = viewModelScope.launch {
        savedNewsUseCase.execute(article)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }
}