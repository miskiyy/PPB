package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.screens.DetailScreen
import com.example.newsapp.ui.screens.HomeScreen
import com.example.newsapp.viewmodel.NewsViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val viewModel: NewsViewModel = viewModel()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel
            ) { article ->
                viewModel.selectArticle(article)
                navController.navigate("detail")
            }
        }
        composable("detail") {
            val article by viewModel.selectedArticle.collectAsState()
            val savedArticles by viewModel.savedArticles.collectAsState()
            article?.let { selected ->
                val isBookmarked = savedArticles.contains(selected)
                DetailScreen(
                    article = selected,
                    isBookmarked = isBookmarked,
                    onBookmarkClick = { viewModel.toggleBookmark(selected) },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
