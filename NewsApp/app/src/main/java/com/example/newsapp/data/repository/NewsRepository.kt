package com.example.newsapp.data.repository

import com.example.newsapp.data.api.RetrofitClient

class NewsRepository {
    companion object {
        const val API_KEY = "YOUR_API_KEY"
    }

    suspend fun getNews() =
        RetrofitClient.apiService
            .getTopHeadlines(
                apiKey = API_KEY
            )
}
