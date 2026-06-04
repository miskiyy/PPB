package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    
    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle = _selectedArticle.asStateFlow()

    private val _isDemoMode = MutableStateFlow(false)
    val isDemoMode = _isDemoMode.asStateFlow()

    // Saved/Bookmarked Articles
    private val _savedArticles = MutableStateFlow<Set<Article>>(emptySet())
    val savedArticles = _savedArticles.asStateFlow()
    
    init {
        loadNews()
    }
    
    fun loadNews() {
        viewModelScope.launch {
            try {
                _uiState.value = NewsUiState.Loading
                if (NewsRepository.API_KEY == "YOUR_API_KEY" || NewsRepository.API_KEY.isBlank()) {
                    _isDemoMode.value = true
                    _uiState.value = NewsUiState.Success(getMockArticles())
                } else {
                    val response = repository.getNews()
                    _isDemoMode.value = false
                    _uiState.value = NewsUiState.Success(response.articles)
                }
            } catch (e: Exception) {
                // If network/API fails, fall back to mock data so the app remains interactive
                _isDemoMode.value = true
                _uiState.value = NewsUiState.Success(getMockArticles())
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectArticle(article: Article?) {
        _selectedArticle.value = article
    }

    fun toggleBookmark(article: Article) {
        if (_savedArticles.value.contains(article)) {
            _savedArticles.value = _savedArticles.value - article
        } else {
            _savedArticles.value = _savedArticles.value + article
        }
    }

    private fun getMockArticles(): List<Article> {
        return listOf(
            Article(
                title = "Android 16 Released with Powerful New Features",
                description = "Android 16 brings exciting new features and improvements including advanced privacy controls, better performance, and enhanced customization options for users worldwide.",
                content = "Android 16 brings exciting new features and improvements including advanced privacy controls, better performance, and enhanced customization options for users worldwide. It also introduces major security patches and developer APIs for next-gen devices.",
                author = "TECHNOLOGY",
                urlToImage = "https://images.unsplash.com/photo-1607604276583-eef5d076aa5f?w=800",
                publishedAt = "2 hours ago"
            ),
            Article(
                title = "AI Revolution Changing the World in 2024",
                description = "Artificial Intelligence continues to reshape software engineering, healthcare, and finance industries around the world.",
                content = "Artificial Intelligence continues to reshape software engineering, healthcare, and finance industries around the world. Tech leaders predict a massive shift in human-computer interfaces over the next few years.",
                author = "TECHNOLOGY",
                urlToImage = "https://images.unsplash.com/photo-1677442136019-21780efad99a?w=800",
                publishedAt = "3 hours ago"
            ),
            Article(
                title = "SpaceX Launches New Starship Successfully",
                description = "The latest launch marks a major milestone in space exploration and reusable rocket technology.",
                content = "The latest launch marks a major milestone in space exploration and reusable rocket technology. This paves the way for manned deep space exploration to Mars.",
                author = "SCIENCE",
                urlToImage = "https://images.unsplash.com/photo-1541185933-ef5d8ed016c2?w=800",
                publishedAt = "5 hours ago"
            ),
            Article(
                title = "Global Economy Shows Positive Growth",
                description = "Economic indicators suggest steady recovery and growth across key markets in the second quarter.",
                content = "Economic indicators suggest steady recovery and growth across key markets in the second quarter. Inflation has stabilized across major economies.",
                author = "BUSINESS",
                urlToImage = "https://images.unsplash.com/photo-1590283603385-17ffb3a7f29f?w=800",
                publishedAt = "6 hours ago"
            ),
            Article(
                title = "Android Tips and Tricks You Should Know",
                description = "Maximize your productivity and device customization with these expert Android tips.",
                content = "Maximize your productivity and device customization with these expert Android tips. Learn how to optimize battery, manage secure notifications, and use custom shortcuts.",
                author = "TECHNOLOGY",
                urlToImage = "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800",
                publishedAt = "1 day ago"
            ),
            Article(
                title = "Best Android Apps In 2024",
                description = "Our curated list of the absolute best productivity, utility, and design apps for Android this year.",
                content = "Our curated list of the absolute best productivity, utility, and design apps for Android this year. Find out which apps made the cut for our annual recommendations.",
                author = "TECHNOLOGY",
                urlToImage = "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800",
                publishedAt = "2 days ago"
            )
        )
    }
}
